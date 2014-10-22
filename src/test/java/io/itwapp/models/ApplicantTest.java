package io.itwapp.models;

import io.itwapp.Itwapp;
import io.itwapp.exception.InvalidRequestError;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import org.junit.FixMethodOrder;

import java.util.*;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApplicantTest {

    private static String interviewId = null;
    private static String applicantId = null;

    @BeforeClass
    public static void setUpBeforeClass()  {
        Itwapp.apiKey = System.getenv("itwappApiKey");
        Itwapp.secretKey = System.getenv("itwappApiSecret");

        //Create an interview
        Map<String, Object> param = new HashMap<String, Object>();

        param.put("name", "interview 1");
        param.put("video", "");
        param.put("text", "");

        Map<String, Object> question = new HashMap<String, Object>();

        question.put("content", "question 1");
        question.put("readingTime", 60);
        question.put("answerTime", 60);
        question.put("number", 1);

        List<Map<String, Object>> questions = new ArrayList<Map<String, Object>>();
        questions.add(question);

        param.put("questions", questions);

        Interview interview = Interview.create(param);
        ApplicantTest.interviewId = interview.id;
    }

    @AfterClass
    public static void tearDownAfterClass()    {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("withApplicant", true);
        // Delete the interview
        Interview.delete(ApplicantTest.interviewId, param);
    }

    @Test(expected = InvalidRequestError.class)
    public void a_testCreateWithMissingParamMail()    {

        Map<String, Object> param = new HashMap<String, Object>();
        //param.put("mail", "jerome@itwapp.io");
        param.put("alert", false);
        param.put("interview", "53fb562418060018063095dd");
        param.put("lang", "en");
        param.put("deadline", 1409045626568L);
        
        Applicant.create(param);
    }

    @Test(expected = InvalidRequestError.class)
    public void b_testCreateWithMissingParamAlert()    {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("mail", "jerome@itwapp.io");
        //param.put("alert", false);
        param.put("interview", "53fb562418060018063095dd");
        param.put("lang", "en");
        param.put("deadline", 1409045626568L);

        Applicant.create(param);
    }

    @Test(expected = InvalidRequestError.class)
    public void c_testCreateWithMissingParamLang()    {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("mail", "jerome@itwapp.io");
        param.put("alert", false);
        param.put("interview", "53fb562418060018063095dd");
        //param.put("lang", "en");
        param.put("deadline", 1409045626568L);

        Applicant.create(param);
    }

    @Test(expected = InvalidRequestError.class)
    public void d_testCreateWithMissingParamDeadLine()    {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("mail", "jerome@itwapp.io");
        param.put("alert", false);
        param.put("interview", "53fb562418060018063095dd");
        param.put("lang", "en");
        //param.put("deadline", 1409045626568L);

        Applicant.create(param);
    }

    @Test(expected = InvalidRequestError.class)
    public void e_testCreateWithMissingParamInterviewAndQuestion()    {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("mail", "jerome@itwapp.io");
        param.put("alert", false);
        //param.put("interview", "53fb562418060018063095dd");
        param.put("lang", "en");
        param.put("deadline", 1409045626568L);

        Applicant.create(param);
    }

    @Test(expected = InvalidRequestError.class)
    public void f_testCreateWithWrongParamQuestion()    {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("mail", "jerome@itwapp.io");
        param.put("alert", false);
        param.put("questions", "");
        param.put("lang", "en");
        param.put("deadline", 1409045626568L);

        Applicant.create(param);

    }

    @Test(expected = InvalidRequestError.class)
    public void g_testCreateWithMissingParamContentOnQuestion()    {

        Map<String, Object> question = new HashMap<String, Object>();
        //question.put("content", "question 1");
        question.put("readingTime", 60);
        question.put("answerTime", 60);
        question.put("number", 1);

        List<Map<String, Object>> questions = new ArrayList<Map<String, Object>>();
        questions.add(question);

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("mail", "jerome@itwapp.io");
        param.put("alert", false);
        param.put("questions", questions);
        param.put("lang", "en");
        param.put("deadline", 1409045626568L);

        Applicant.create(param);
    }

    @Test(expected = InvalidRequestError.class)
    public void h_testCreateWithEmptyParamQuestion()    {
        List<Map<String, Object>> questions = new ArrayList<Map<String, Object>>();

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("mail", "jerome@itwapp.io");
        param.put("alert", false);
        param.put("questions", questions);
        param.put("lang", "en");
        param.put("deadline", 1409045626568L);

        Applicant.create(param);
    }

    @Test(expected = InvalidRequestError.class)
    public void i_testCreateWithMissingParamReadingTimeOnQuestion()    {
        Map<String, Object> question = new HashMap<String, Object>();
        question.put("content", "question 1");
        //question.put("readingTime", 60);
        question.put("answerTime", 60);
        question.put("number", 1);

        List<Map<String, Object>> questions = new ArrayList<Map<String, Object>>();
        questions.add(question);

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("mail", "jerome@itwapp.io");
        param.put("alert", false);
        param.put("questions", questions);
        param.put("lang", "en");
        param.put("deadline", 1409045626568L);

        Applicant.create(param);
    }

    @Test(expected = InvalidRequestError.class)
    public void j_testCreateWithWrongParamReadingTimeOnQuestion()    {
        Map<String, Object> question = new HashMap<String, Object>();
        question.put("content", "question 1");
        question.put("readingTime", 72);
        question.put("answerTime", 60);
        question.put("number", 1);

        List<Map<String, Object>> questions = new ArrayList<Map<String, Object>>();
        questions.add(question);

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("mail", "jerome@itwapp.io");
        param.put("alert", false);
        param.put("questions", questions);
        param.put("lang", "en");
        param.put("deadline", 1409045626568L);

        Applicant.create(param);
    }

    @Test(expected = InvalidRequestError.class)
    public void k_testCreateWithMissingParamAnswerTimeOnQuestion()    {
        Map<String, Object> question = new HashMap<String, Object>();
        question.put("content", "question 1");
        question.put("readingTime", 60);
        //question.put("answerTime", 60);
        question.put("number", 1);

        List<Map<String, Object>> questions = new ArrayList<Map<String, Object>>();
        questions.add(question);

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("mail", "jerome@itwapp.io");
        param.put("alert", false);
        param.put("questions", questions);
        param.put("lang", "en");
        param.put("deadline", 1409045626568L);

        Applicant.create(param);
    }

    @Test(expected = InvalidRequestError.class)
    public void l_testCreateWithWrongParamAnswerTimeOnQuestion()    {
        Map<String, Object> question = new HashMap<String, Object>();
        question.put("content", "question 1");
        question.put("readingTime", 60);
        question.put("answerTime", 45);
        question.put("number", 1);

        List<Map<String, Object>> questions = new ArrayList<Map<String, Object>>();
        questions.add(question);

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("mail", "jerome@itwapp.io");
        param.put("alert", false);
        param.put("questions", questions);
        param.put("lang", "en");
        param.put("deadline", 1409045626568L);

        Applicant.create(param);
    }

    @Test(expected = InvalidRequestError.class)
    public void m_testCreateWithMissingParamNumberOnQuestion()    {
        Map<String, Object> question = new HashMap<String, Object>();
        question.put("content", "question 1");
        question.put("readingTime", 60);
        question.put("answerTime", 45);
        //question.put("number", 1);

        List<Map<String, Object>> questions = new ArrayList<Map<String, Object>>();
        questions.add(question);

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("mail", "jerome@itwapp.io");
        param.put("alert", false);
        param.put("questions", questions);
        param.put("lang", "en");
        param.put("deadline", 1409045626568L);

        Applicant.create(param);
    }

    @Test
    public void n_testCreateWithInterviewId() {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("mail", "jerome@itwapp.io");
        param.put("alert", false);
        param.put("interview", ApplicantTest.interviewId);
        param.put("lang", "en");
        param.put("deadline", 1409045626568L);

        Applicant applicant = Applicant.create(param);

        assertEquals("jerome@itwapp.io", applicant.mail);
        assertFalse(applicant.deleted);
        ApplicantTest.applicantId = applicant.id;
    }

    @Test
    public void o_testDelete() {
        assertNotNull(ApplicantTest.applicantId);

        Applicant.delete(ApplicantTest.applicantId);

        Applicant applicant = Applicant.findOne(ApplicantTest.applicantId);
        assertTrue(applicant.deleted);
    }

    @Test
    public void p_testFindAllApplicant()  {
        Applicant[] all = Interview.findAllApplicant(ApplicantTest.interviewId, new HashMap<String, Object>());

        assertEquals(1, all.length);
        assertEquals(ApplicantTest.applicantId, all[0].id);

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("next", ApplicantTest.applicantId);
        Applicant[] empty_list = Interview.findAllApplicant(ApplicantTest.interviewId, param);
        assertEquals(0, empty_list.length);
    }

    @Test
    public void q_testCreateWithQuestion() throws InterruptedException {

        Map<String, Object> question = new HashMap<String, Object>();
        question.put("content", "question 1");
        question.put("readingTime", 60);
        question.put("answerTime", 60);
        question.put("number", 1);

        List<Map<String, Object>> questions = new ArrayList<Map<String, Object>>();
        questions.add(question);

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("mail", "jerome@itwapp.io");
        param.put("alert", false);
        param.put("questions", questions);
        param.put("lang", "en");
        param.put("deadline", 1409045626568L);

        Applicant applicant = Applicant.create(param);

        assertEquals("jerome@itwapp.io", applicant.mail);
        assertFalse(applicant.deleted);
        assertEquals(1, applicant.questions.length);
        assertEquals("http://itwapp.io", applicant.callback);

        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("withApplicant", true);

        Interview.delete(applicant.interview, parameter);

        Applicant app = Applicant.findOne(applicant.id);
        assertTrue(app.deleted);
    }

    @Test
    public void r_testCreateWithQuestionAndCallback() throws InterruptedException {

        Map<String, Object> question = new HashMap<String, Object>();
        question.put("content", "question 1");
        question.put("readingTime", 60);
        question.put("answerTime", 60);
        question.put("number", 1);

        List<Map<String, Object>> questions = new ArrayList<Map<String, Object>>();
        questions.add(question);

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("mail", "jerome@itwapp.io");
        param.put("alert", false);
        param.put("questions", questions);
        param.put("lang", "en");
        param.put("deadline", 1409045626568L);
        param.put("callback", "http://mycustomeurl.com/done");

        Applicant applicant = Applicant.create(param);

        assertEquals("jerome@itwapp.io", applicant.mail);
        assertFalse(applicant.deleted);
        assertEquals(1, applicant.questions.length);
        assertEquals("http://mycustomeurl.com/done", applicant.callback);

        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("withApplicant", true);

        Interview.delete(applicant.interview, parameter);

        Applicant app = Applicant.findOne(applicant.id);
        assertTrue(app.deleted);
    }



}
