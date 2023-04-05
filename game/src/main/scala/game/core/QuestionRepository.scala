package game.core

import game.core.Question
trait QuestionRepository {
    def nextQuestion(): Question
}

object QuestionRepository {
    def apply(): QuestionRepository = QuestionRepositoryImpl()
}

class QuestionRepositoryImpl extends QuestionRepository {
  override def nextQuestion(): Question = ???
}
