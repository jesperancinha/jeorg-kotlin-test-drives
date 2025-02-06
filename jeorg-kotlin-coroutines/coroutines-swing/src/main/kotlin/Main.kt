package org.jesperancinha.ktd


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.SwingUtilities

fun main() {
    SwingUtilities.invokeLater {
        val frame = JFrame("Kotlin Coroutines with Dispatchers.Main")
        val label = JLabel("Click the button to start", JLabel.CENTER)
        val button = JButton("Start Task")

        button.addActionListener {
            CoroutineScope(Dispatchers.Main).launch {
                label.text = "Running task..."

                // Simulate a heavy computation or IO operation
                withContext(Dispatchers.IO) {
                    Thread.sleep(3000) // Simulate delay
                }

                // Back to Main thread
                label.text = "Task Completed!"
            }
        }

        frame.layout = BorderLayout()
        frame.add(label, BorderLayout.CENTER)
        frame.add(button, BorderLayout.SOUTH)
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.preferredSize = Dimension(400, 200)
        frame.pack()
        frame.isVisible = true
    }
}
