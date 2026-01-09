package org.example.gems.data.mock

data class Student(
    val name: String,
    val usn: String,
    val branch: String,
    val course: String,
    val semester: Int,
    val section: String,
    val email: String,
    val phone: String,
    val fatherName: String,
    val fatherPhone: String,
    val motherName: String,
    val motherPhone: String,
    val communicationAddress: String,
    val permanentAddress: String
)

data class Attendance(
    val subject: String,
    val classesConducted: Int,
    val classesAttended: Int,
    val percentage: Double
)

data class Marks(
    val subject: String,
    val ia1: String,
    val ia2: String,
    val ia3: String,
    val total: String
)

data class TimetableSlot(
    val period: Int,
    val subject: String,
    val faculty: String,
    val timeSlot: String
)

data class Notice(
    val id: String,
    val title: String,
    val date: String,
    val description: String,
    val isRead: Boolean = false
)

object MockData {
    val student = Student(
        name = "Sathish Kumar",
        usn = "1RV21CS123",
        branch = "Computer Science & Engineering",
        course = "B.E. (Bachelor of Engineering)",
        semester = 6,
        section = "A",
        email = "sathish.cs21@rvce.edu.in",
        phone = "+91 9876543210",
        fatherName = "Rajesh Kumar",
        fatherPhone = "+91 9123456780",
        motherName = "Sumathi Rajesh",
        motherPhone = "+91 9123456781",
        communicationAddress = "#12, 4th Cross, Malleshwaram, Bengaluru, Karnataka - 560003",
        permanentAddress = "#12, 4th Cross, Malleshwaram, Bengaluru, Karnataka - 560003"
    )

    val attendanceList = listOf(
        Attendance("Software Engineering", 40, 36, 90.0),
        Attendance("Machine Learning", 45, 38, 84.4),
        Attendance("Compiler Design", 38, 30, 78.9),
        Attendance("Computer Networks", 42, 40, 95.2),
        Attendance("Cloud Computing", 35, 32, 91.4)
    )

    val marksList = listOf(
        Marks("Software Engineering", "23", "21", "24", "68/75"),
        Marks("Machine Learning", "19", "22", "20", "61/75"),
        Marks("Compiler Design", "25", "24", "25", "74/75"),
        Marks("Computer Networks", "22", "20", "21", "63/75")
    )

    val weeklyTimetable = mapOf(
        "Mon" to listOf(
            TimetableSlot(1, "Machine Learning", "Dr. Smith", "09:00 - 10:00"),
            TimetableSlot(2, "Compiler Design", "Prof. Johnson", "10:00 - 11:00"),
            TimetableSlot(3, "Software Engineering", "Dr. Davis", "11:15 - 12:15"),
            TimetableSlot(4, "Lunch Break", "-", "12:15 - 13:15"),
            TimetableSlot(5, "Computer Networks Lab", "Prof. Wilson", "13:15 - 15:15"),
            TimetableSlot(6, "Library Session", "-", "15:30 - 16:30")
        ),
        "Tue" to listOf(
            TimetableSlot(1, "Compiler Design", "Prof. Johnson", "09:00 - 10:00"),
            TimetableSlot(2, "Computer Networks", "Dr. White", "10:00 - 11:00"),
            TimetableSlot(3, "Machine Learning", "Dr. Smith", "11:15 - 12:15"),
            TimetableSlot(4, "Lunch Break", "-", "12:15 - 13:15"),
            TimetableSlot(5, "Software Engineering Lab", "Dr. Davis", "13:15 - 15:15")
        ),
        "Wed" to listOf(
            TimetableSlot(1, "Software Engineering", "Dr. Davis", "09:00 - 10:00"),
            TimetableSlot(2, "Cloud Computing", "Prof. Miller", "10:00 - 11:00"),
            TimetableSlot(3, "Math IV", "Dr. Gray", "11:15 - 12:15"),
            TimetableSlot(4, "Lunch Break", "-", "12:15 - 13:15"),
            TimetableSlot(5, "Machine Learning Lab", "Dr. Smith", "13:15 - 15:15")
        ),
        "Thu" to listOf(
            TimetableSlot(1, "Machine Learning", "Dr. Smith", "09:00 - 10:00"),
            TimetableSlot(2, "Compiler Design", "Prof. Johnson", "10:00 - 11:00"),
            TimetableSlot(3, "Computer Networks", "Dr. White", "11:15 - 12:15"),
            TimetableSlot(4, "Lunch Break", "-", "12:15 - 13:15"),
            TimetableSlot(5, "Seminar/Project", "-", "13:15 - 15:15")
        ),
        "Fri" to listOf(
            TimetableSlot(1, "Cloud Computing", "Prof. Miller", "09:00 - 10:00"),
            TimetableSlot(2, "Software Engineering", "Dr. Davis", "10:00 - 11:00"),
            TimetableSlot(3, "Open Elective", "-", "11:15 - 12:15"),
            TimetableSlot(4, "Lunch Break", "-", "12:15 - 13:15"),
            TimetableSlot(5, "Sports/Cultural", "-", "13:15 - 16:30")
        ),
        "Sat" to listOf(
            TimetableSlot(1, "Project Review", "Panel", "09:00 - 11:00"),
            TimetableSlot(2, "Mentor Meeting", "Mentor", "11:15 - 12:15")
        )
    )

    val notices = listOf(
        Notice("1", "Internal Assessment III Dates", "2026-01-10", "The IA-III will be held starting from Jan 15th. Please check your department notice board for the detailed schedule."),
        Notice("2", "Holiday Announcement", "2026-01-08", "The college will remain closed on Jan 14th on account of Makar Sankranti."),
        Notice("3", "Seminar on AI/ML", "2026-01-05", "A guest lecture on the 'Future of Generative AI' is scheduled in the main auditorium tomorrow at 10 AM.")
    )
}
