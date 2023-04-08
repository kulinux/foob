package game.core

import game.core.Question

trait QuestionRepository {
  def nextQuestion(): Question
  def verify(play: Play): Boolean
}
