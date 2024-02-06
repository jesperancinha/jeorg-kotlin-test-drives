package org.jesperancinha.ktd.crums1

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

enum class RoomClass {
    HIGH_CLASS,
    LOW_CLASS
}

class HotelRoomAlgorithm {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            val pukeIndexCheck = mapOf<String, Long>(
                "toilet" to 10,
                "pdc_bag" to 20,
                "pdc_bin" to 30,
                "paper_bin" to 5,
                "bidet" to 5,
                "balcony" to 1,
                "grocery_bag" to 9,
                "welcome_bag" to 10,
                "emergency_phone" to 10
            )
            val sympathyIndexCheck = mapOf<String, Long>(
                "reception" to 20,
                "professional_language" to 10,
                "vulgar_language" to -5,
                "blaming_language" to -10,
                "pedantic_language" to -20,
                "availability" to 10,
                "appointment_efficiency" to 20,
                "schedule_automation" to 20,
                "empathetic_language" to 20,
            )
            ConsolerizerComposer.outSpace()
                .cyan(ConsolerizerComposer.title("Crum 2 - Hashmaps in Kotlin"))
                .magenta("Case: PukingDotCom is a company that exports bags and bins to travellers who run the risk of feeling sick while travelling")
                .magenta("However, the reach of this company is not the same in all regions and cities across the world.")
                .magenta("Therefore, PukingDotCom, provides an index of availability to travellers who want to feel safe when suffering from these symptoms")
                .magenta("Compliance levels are measure by a static definition of scores")
                .magenta("If the compliance result is higher than 75 we rate it as high-class")
                .magenta("If the compliance result is lower or equal to  75 we rate it as low-class")
                .green("This is the static check table for that")
                .green(pukeIndexCheck)
                .magenta("PukingDotCom is not only busy with people feeling sick while travelling")
                .magenta("PukingDotCom also wants people to feel respected and appreciated")
                .magenta("There is, therefore, a sympathy index")
                .magenta("Compliance levels are measured in the same way")
                .magenta("If the compliance result is higher than 75 we rate it as high-class")
                .magenta("If the compliance result is lower or equal to  75 we rate it as low-class")
                .green("This is the static check table for that")
                .green(sympathyIndexCheck)
                .reset();


            val room1 = listOf("toilet", "bidet", "grocery_bag", "paper_bin")
            val auditResult1: AuditResult = auditResult(room1, pukeIndexCheck, sympathyIndexCheck)


            val room2 = listOf("pdc_bag",
                "pdc_bin",
                "grocery_bag",
                "paper_bin",
                "toilet",
                "appointment_efficiency",
                "schedule_automation",
                "empathetic_language",
                "reception")
            val auditResult2: AuditResult = auditResult(room2, pukeIndexCheck, sympathyIndexCheck)

            ConsolerizerComposer.outSpace()
                .cyan("The result for room1 is")
                .black()
                .bgGreen(auditResult1)
            ConsolerizerComposer.outSpace()
                .cyan("The result for room2 is")
                .black()
                .bgGreen(auditResult2)
        }

        private fun auditResult(
            room1: List<String>,
            pukeIndexCheck: Map<String, Long>,
            sympathyIndexCheck: Map<String, Long>,
        ): AuditResult {
            val pukeIndex: Long = room1.map {
                pukeIndexCheck.getOrDefault(it, 0)
            }.sumOf { it }

            val sympathyIndex: Long = room1.map {
                sympathyIndexCheck.getOrDefault(it, 0)
            }.sumOf { it }

            val auditResult: AuditResult;
            if (pukeIndex > sympathyIndex) {
                auditResult = AuditResult(
                    if (pukeIndex > 75) RoomClass.HIGH_CLASS else RoomClass.LOW_CLASS, ComplianceType.Puke,
                    pukeIndex
                )
            } else {
                auditResult = AuditResult(
                    if (sympathyIndex > 75) RoomClass.HIGH_CLASS else RoomClass.LOW_CLASS, ComplianceType.Sympathy,
                    sympathyIndex
                )
            }
            return auditResult
        }
    }
}

enum class ComplianceType {
    Puke,
    Sympathy
}

data class AuditResult(
    val roomClass: RoomClass,
    val complianceType: ComplianceType,
    val score: Long,
) {
}