package controllers

import models.Question
import kotlin.random.Random

class QuestionAPI {
    private var questions = ArrayList<Question>();

    fun addQuestion(question : Question): Boolean {
        return questions.add(question);
    }

    fun removeQuestion(indexToDelete: Int): Question? {
        return if(isValidListIndex(indexToDelete, questions)) {
            questions.removeAt(indexToDelete);
        } else null
    }

    fun numberOfQuestions(): Int {
        return questions.size;
    }

   fun listAllQuestions(): String {
        return if(questions.isEmpty()) {
            "There are no Questions in the System. Add one!"
        } else {
            var listOfAllQuestions = "";
            for(q in questions) {
                listOfAllQuestions += "Question Number: " + q.questionNumber + "\n" + "Question: " + q.theQuestion + "\n";
            }
            listOfAllQuestions;
        }
    }

    fun updateQuestion(indexToUpdate: Int, question: Question?): Boolean {
        val updateQuestion = findQuestion(indexToUpdate);

        if((updateQuestion != null) && (question != null)) {
            updateQuestion.questionNumber = question.questionNumber;
            updateQuestion.theQuestion = question.theQuestion;
            updateQuestion.possibleAnswers = question.possibleAnswers;
            updateQuestion.questionAnswer = question.questionAnswer;
            updateQuestion.questionDifficultyLevel = question.questionDifficultyLevel;
            return true;
        }
        return false;
    }

    fun allQuestionsByAscendingDifficulty(): String {
        var allQuestions = "";
        var orderedQuestions = questions.sortedBy { it.questionDifficultyLevel }

        for(q in orderedQuestions) {
            allQuestions += "Question Number: ${q.questionNumber}\n" + "Question: ${q.theQuestion}\n " + "Question Difficulty Level: ${q.questionDifficultyLevel}\n";
        }
        return allQuestions;
    }

    fun randomQuestion(): Question? {

        if(questions.isEmpty()) {
            return null;
        }
        var randomIndex = Random.nextInt(questions.size);
        var randomQuestion = questions[randomIndex];
        return randomQuestion;
    }

    fun findQuestion(index: Int): Question? {
        return if(isValidListIndex(index, questions)) {
            questions[index]
        } else null
    }

    fun isValidListIndex(index: Int, list: List<Any>): Boolean {
        return (index >= 0 && index < list.size)
    }
}

