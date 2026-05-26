package com.senai.abcgjl_smartcuisine_mobile.app.navigation

sealed class AppDestinations(val route: String) {
    data object Login : AppDestinations("login")
    data object Signup : AppDestinations("signup")
    data object Home : AppDestinations("home")
    data object AdmHome : AppDestinations("adm_home")
    data object GerenteHome : AppDestinations("gerente_home")
    data object CozinheiroHome : AppDestinations("cozinheiro_home")
    //data object TeacherClassDetail : AppDestinations("teacher_class_detail/{classId}") {
       // fun createRoute(classId: String): String = "teacher_class_detail/$classId"
    //}
    //data object TeacherStudentDetail : AppDestinations("teacher_student_detail/{studentId}") {
        //fun createRoute(studentId: String): String = "teacher_student_detail/$studentId"
    //}
    //data object StudentUcDetail : AppDestinations("student_uc_detail/{ucId}") {
        //fun createRoute(ucId: String): String = "student_uc_detail/$ucId"
    //}
    //data object StudentCard : AppDestinations("student_card")
}