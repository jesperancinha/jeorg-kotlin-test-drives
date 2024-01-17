package org.jesperancinha.ktd

import jakarta.validation.Validation
import jakarta.validation.constraints.Email


data class User (
    @field:Email(message = "Please insert a valid email")
    val email:String
)

class ValidationUsersEmail {
  companion object {
      @JvmStatic
      fun main(args: Array<String> = emptyArray()) {
          val factory = Validation.buildDefaultValidatorFactory()
          val validator = factory.validator
          val validate1 = validator.validate(User("bad"))
          println(validate1)
          val validate2 = validator.validate(User("user@someemail.com"))
          println(validate2)
      }
  }
}