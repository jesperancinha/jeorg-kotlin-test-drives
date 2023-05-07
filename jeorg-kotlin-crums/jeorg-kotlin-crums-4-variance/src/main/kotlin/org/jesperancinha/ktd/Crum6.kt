package org.jesperancinha.ktd

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.math.BigDecimal

open class Item(
    private val weight: BigDecimal
) {
    open fun description() = "This ${this.javaClass.simpleName.lowercase()} weighs $weight"
}

data class Brick(
    val weight: BigDecimal
) : Item(weight)

data class Book(
    val weight: BigDecimal
) : Item(weight)

data class InvariantWarehouse<T : Item>(
    val itemsList: MutableList<T>
) {
    fun printItemsPretty() = itemsList.joinToString(",") { it.description() }

    fun addItem(item: T) = itemsList.apply { add(item) }
}

data class CovariantWarehouse<out T : Item>(
    val itemsList: MutableList<out T>
) {
    fun printItemsPretty() = logger.info(itemsList.joinToString(",") { it.description() })

    // This is not possible. T is declared as out
    // fun addItem(item: T) = itemsList.apply { add(item) }
    fun findFirstItem() = itemsList[0]

    companion object {
        val logger: Logger = LoggerFactory.getLogger(CovariantWarehouse::class.java)
    }
}

data class ContravariantWarehouse<in T : Item>(
    private val itemsList: MutableList<in T>
) {
    @Suppress("UNCHECKED_CAST")
    fun printItemsPretty() = logger.info(itemsList.joinToString(",") {
//        it.description()
        // It results into Any because we specified that T only applies to types on the `in` side
        // ContravariantWarehouse is contravariant for type T
        // So this means that T isn't supposed to be used as out
        // This result in the type being erased, which is different from what happens in types on the `out` side.
        (it as T).description()
    })

    fun addItem(item: T) = itemsList.apply { add(item) }

    fun findFirstItem() = itemsList[0]

    companion object {
        val logger: Logger = LoggerFactory.getLogger(ContravariantWarehouse::class.java)
    }
}


class WarehouseManager {
    companion object {

        private val logger: Logger = LoggerFactory.getLogger(WarehouseManager::class.java)

        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            runContravariantTest()

            runCovariantTest()

            runInvariantTest()
        }

        @Suppress("UNCHECKED_CAST")
        private fun runInvariantTest() {
            logger.info("Starting runInvariantTest")
            val contravariantWarehouse = InvariantWarehouse(mutableListOf<Brick>())
            contravariantWarehouse.printItemsPretty()
            val contravariantWarehouse2 = InvariantWarehouse(mutableListOf<Book>())
            contravariantWarehouse2.printItemsPretty()
            val contravariantWarehouse3 = InvariantWarehouse(mutableListOf())
            contravariantWarehouse3.printItemsPretty()

            val contravariantWarehouseBrick: InvariantWarehouse<Brick> =
                InvariantWarehouse(mutableListOf(Book(123.toBigDecimal()))) as InvariantWarehouse<Brick>
            contravariantWarehouseBrick.addItem(Brick(123.toBigDecimal()))
            contravariantWarehouseBrick.printItemsPretty()
            val contravariantWarehouseBook: InvariantWarehouse<Book> =
                InvariantWarehouse(mutableListOf(Brick(123.toBigDecimal()))) as InvariantWarehouse<Book>
            contravariantWarehouseBook.addItem(Book(123.toBigDecimal()))
            contravariantWarehouseBook.printItemsPretty()
            val contravariantWarehouseItem: InvariantWarehouse<Item> =
                InvariantWarehouse(mutableListOf(Book(123.toBigDecimal()))) as InvariantWarehouse<Item>
            contravariantWarehouseItem.addItem(Item((123.toBigDecimal())))
            contravariantWarehouseItem.printItemsPretty()
            logger.info("With Invariance types, you have to use explicit casting and hope for the best")

        }

        @Suppress("UNREACHABLE_CODE", "CAST_NEVER_SUCCEEDS")
        private fun runCovariantTest() {
            logger.info("Starting runCovariantTest")
            val contravariantWarehouse = CovariantWarehouse(mutableListOf<Brick>())
            contravariantWarehouse.printItemsPretty()
            val contravariantWarehouse2 = CovariantWarehouse(mutableListOf<Book>())
            contravariantWarehouse2.printItemsPretty()
            run {
                val contravariantWarehouseBook: CovariantWarehouse<Item> = CovariantWarehouse(mutableListOf<Book>())
                try {
                    contravariantWarehouseBook.itemsList.add(Book(123.toBigDecimal()) as Nothing)
                } catch (ex: Exception) {
                    logger.error("ERROR (... but expected)", ex)
                    logger.info("When we define out parameters, it is not possible to use them as input parameters... or is it?")
                    logger.info("We can use annotations to ignore this, but that defeats the whole purpose of using out")
                }
            }
            val contravariantWarehouseItem: CovariantWarehouse<Item> =
                CovariantWarehouse(mutableListOf(Book(999.toBigDecimal())))
            contravariantWarehouseItem.printItemsPretty()
            val firstItem = contravariantWarehouseItem.findFirstItem()
            logger.info("This is the first item: $firstItem")
        }

        @Suppress("UNCHECKED_CAST")
        private fun runContravariantTest() {
            logger.info("Starting runContravariantTest")
            val contravariantWarehouse: ContravariantWarehouse<Item> =
                ContravariantWarehouse(mutableListOf<Brick>()) as ContravariantWarehouse<Item>
            contravariantWarehouse.addItem(Item(234.toBigDecimal()))
            contravariantWarehouse.printItemsPretty()
            val contravariantWarehouse2 = ContravariantWarehouse(mutableListOf<Book>())
            contravariantWarehouse2.printItemsPretty()
            val contravariantWarehouseBook: ContravariantWarehouse<Book> = ContravariantWarehouse(mutableListOf<Item>())
            contravariantWarehouseBook.addItem(Book(123.toBigDecimal()))

            // It does not accept bricks...
            // contravariantWarehouseBook.addItem(Brick(123.toBigDecimal()))

            // However if we cast it:
            (contravariantWarehouseBook as ContravariantWarehouse<Brick>).addItem(Brick(123.toBigDecimal()))
            // And now we can add another one:
            contravariantWarehouseBook.addItem(Brick(124.toBigDecimal()))
            // Which makes contravariant types very dynamic
            contravariantWarehouseBook.printItemsPretty()
            (contravariantWarehouseBook as ContravariantWarehouse<Item>).addItem(Item(123.toBigDecimal()))
            contravariantWarehouseBook.printItemsPretty()
            contravariantWarehouseBook.addItem(Item(123.toBigDecimal()))
            contravariantWarehouseBook.printItemsPretty()

            // Casting to item will not happen because WarehouseBook is contravariant to type T
            // Since this is a type on the in side, its retrieval from the list will result in Any type
            // val item: Item = contravariantWarehouseBook.findFirstItem()
            // We do know that our type is an Item, and so we can cast it safely.
            val item: Item = contravariantWarehouseBook.findFirstItem() as Item
            println(item.description())
        }
    }
}