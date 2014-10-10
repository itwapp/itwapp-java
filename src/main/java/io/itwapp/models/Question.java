package io.itwapp.models;

public class Question {

    /**
     *  This is the question.
     */
    public String content;

    /**
     * This is the max duration available for the applicant to read the question and prepare the answer. Accepted value are : 60, 120, 180, 240, 300
     */
    public int readingTime;

    /**
     * This is the max duration available for the applicant to answer the question. Accepted value are : 60, 120, 180, 240, 300
     */
    public int answerTime;

    /**
     * This is the position of the question in the interview.
     */
    public int number;

}
