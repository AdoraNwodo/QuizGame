package com.example.nenneadora.quizgame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class QuizHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1; //db version

    private static final String DATABASE_NAME = "Quiz"; //db name

    //TABLE NAMES
    protected static final String TABLE_GENERAL = "general";
    protected static final String TABLE_ZOBIAWA = "zobiawa";
    protected static final String TABLE_IMAGE = "image";


    //COLUMN NAMES FOR ALL TABLES
    private static final String KEY_ID = "qid";
    private static final String KEY_QUESTION = "question";
    private static final String KEY_IMAGE = "image";        //for image quiz
    private static final String KEY_ANSWER = "answer";  //correct option
    private static final String KEY_OPTA = "opta";      //option a
    private static final String KEY_OPTB = "optb";      //option b
    private static final String KEY_OPTC = "optc";      //option c
    private static final String KEY_OPTD = "optd";      //option d

    private SQLiteDatabase dbase;

    private static final String CREATE_TABLE_GENERAL = "CREATE TABLE IF NOT EXISTS "
            + TABLE_GENERAL + " ( "+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_QUESTION + " TEXT, " + KEY_ANSWER + " TEXT, " + KEY_OPTA + " TEXT, "
            + KEY_OPTB + " TEXT, " + KEY_OPTC + " TEXT, "+KEY_OPTD+" TEXT)";

    private static final String CREATE_TABLE_ZOBIAWA = "CREATE TABLE IF NOT EXISTS "
            + TABLE_ZOBIAWA + " ( "+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_QUESTION + " TEXT, " + KEY_ANSWER + " TEXT, " + KEY_OPTA + " TEXT, "
            + KEY_OPTB + " TEXT, " + KEY_OPTC + " TEXT, "+KEY_OPTD+" TEXT)";

    public QuizHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        dbase = db;

        //creating required tables
        db.execSQL(CREATE_TABLE_ZOBIAWA);
        db.execSQL(CREATE_TABLE_GENERAL);
        addGeneralQuestion(); addZobiawaQuestion();
    }

    private void addZobiawaQuestion(){
        Question q1 = new Question("A sub-ethnic group within the Igbo ethnic group ", "Ikwerre","Igaleri", "Umuleri","Akwuleri","Umuleri");
        Question q2 = new Question("What is the full meaning of CAN", "Catholic Association of Nigeria", "Christian Association of Nigeria",
                "Communication Agency of Nigeria","Christian Agency of Nigeria","Christian Association of Nigeria");
        Question q3 = new Question("'Damnosa hereditas' is latin for ","Roman Family Inheritance","Undeserved inheritance",
                "Awesome inheritance","Burdensome inheritance","Burdensome inheritance");
        Question q4 = new Question("Fish out the odd one","Idoma","Igala","Ikwerre","Itsekiri","Ikwerre");
        Question q5 = new Question("Fish out the odd one","Zuruziyya","Maitatsine","Izala","Sanusiyya","Zuruziyya");
        Question q6 = new Question("What is the full meaning of PFN","Protestant fellowship of Nigeria","Pilgrim fellowship Nigeria",
                "Pentecostal fellowship of Nigeria","Pilgrim festival Nigeria","Pentecostal fellowship of Nigeria");
        Question q7 = new Question("Which of these is an ethnic group originating in Cameroon","Khuli-China","Jukun-Chamba","Jama-Samba","Khili-Jama","Jukun-Chamba");
        Question q8 = new Question("Which of these is not an ethnic group","Kataf","Kutebq","Urhobo","Kimasa","Kimasa");
        Question q9 = new Question("What does OIC stand for","Organisation of Islamic Cooperation","Organisation of Islamic Constitution",
                "Organisation of Islamic Conference","Organisation of Islamic Congregation","Organisation of Islamic Cooperation");
        Question q10 = new Question("Fish out the odd one","Bini, Aguleri, Itsekiri","Ijaw, Efik, Bini"
                ,"Ijaw, Efik, Itsekiri","Ijaw, Itsekiri, Bini","Bini, Aguleri, Itsekiri");

        this.addQuestion(q1, TABLE_ZOBIAWA); this.addQuestion(q2, TABLE_ZOBIAWA);
        this.addQuestion(q3, TABLE_ZOBIAWA); this.addQuestion(q4, TABLE_ZOBIAWA);
        this.addQuestion(q5, TABLE_ZOBIAWA); this.addQuestion(q6, TABLE_ZOBIAWA);
        this.addQuestion(q7, TABLE_ZOBIAWA); this.addQuestion(q8, TABLE_ZOBIAWA);
        this.addQuestion(q9, TABLE_ZOBIAWA); this.addQuestion(q10, TABLE_ZOBIAWA);
    }
    private void addGeneralQuestion(){

        //Question Instances
        Question q1 = new Question("Carl and the Passions changed band name to what ", "Phenomenal Boys",
                "Wave Boys", "Beach Boys", "Passion Boys", "Beach Boys");
        Question q2 = new Question("How many rings on the Olympic flag","Four","Six","Five","Seven","Five");
        Question q3 = new Question("What colour is vermilion a shade of", "Blue","Red","Yellow","Green","Red");
        Question q4 = new Question("King Zog ruled which country", "Albania","Uganda","Algeria","Nigeria","Albania");
        Question q5 = new Question("What colour is Spock's blood","Red","Yellow","Blue","Green","Green");
        Question q6 = new Question("Where in your body is your patella","Brain","Chest","Knee","Pelvis","Knee");
        Question q7 = new Question("Where can you find London bridge today","New Jersey","Arizona","New York","Los Angeles","Arizona");
        Question q8 = new Question("Who was the first man in space","Neil Armstrong","Carol Held","Yuri Gagarin","Carol Knight","Yuri Gagarin");
        Question q9 = new Question("If you had pogonophobia what would you be afraid of","Rain","Beards","Sweat","Wind","Beards");
        Question q10 = new Question("Which car company makes the Celica","Honda","Ford","Hyundai","Toyota","Toyota");
        Question q11 = new Question("What kind of food is Cullan Skink","Noodles","Croissant","Fish","Salad","Fish");
        Question q12 = new Question("Which country do Sinologists study","Japan","China","Dubai","India","China");
        Question q13 = new Question("Who wrote about Willie Wonka and the Chocolate Factory","Roald Dahl","Oscar Wilde","Kirk Douglas","Francis Drake","Roald Dahl");
        Question q14 = new Question("Portugal has had six Kings with what first name","James","John","Jude","Joseph","John");
        Question q15 = new Question("Who wrote the book Billy Budd also Moby Dick","Dick Turpin","Vidkun Quisling","Herman Melville","Arthur Ransom","Herman Melville");
        Question q16 = new Question("In which country was Auschwitz","Poland","Finland","Germany","Thailand","Poland");
        Question q17 = new Question("What is the chemical symbol for tungsten","T","Tu","W","J","W");
        Question q18 = new Question("Who was the Roman god of agriculture","Saturn","Uranus","Mercury","Pluto","Saturn");
        Question q19 = new Question("What was the first Carry On film","Carry on Doctors","Carry on Sergeant","Carry on Campus","Carry on Susan","Carry on Sergeant");
        Question q20 = new Question("In what Elvis film did he play a double role","Kissing Cousins","Viva Las Vegas","King Creole","Flaming Star","Kissing Cousins");
        Question q21 = new Question("What is the colour of mourning in Turkey","Pink","Violet","Green","Black","Violet");
        Question q22 = new Question("What flower is the symbol of secrecy","Allium","HIbiscus","Dahlias","Rose","Rose");
        Question q23 = new Question("What items were originally called Hanways","Umbrellas","Hand fans","Bracelets","Scarfs","Umbrellas");
        Question q24 = new Question("Which country grows the most potatoes","China","Russia","India","Peru","Russia");
        Question q25 = new Question("What do Ombrophobes fear","Beards","Sweat","Rain","Wind","Rain");

        this.addQuestion(q1, TABLE_GENERAL);    this.addQuestion(q2, TABLE_GENERAL);
        this.addQuestion(q3, TABLE_GENERAL);    this.addQuestion(q4, TABLE_GENERAL);
        this.addQuestion(q5, TABLE_GENERAL);    this.addQuestion(q6, TABLE_GENERAL);
        this.addQuestion(q7, TABLE_GENERAL);    this.addQuestion(q8, TABLE_GENERAL);
        this.addQuestion(q9, TABLE_GENERAL);    this.addQuestion(q10, TABLE_GENERAL);
        this.addQuestion(q11, TABLE_GENERAL);    this.addQuestion(q12, TABLE_GENERAL);
        this.addQuestion(q13, TABLE_GENERAL);    this.addQuestion(q14, TABLE_GENERAL);
        this.addQuestion(q15, TABLE_GENERAL);    this.addQuestion(q16, TABLE_GENERAL);
        this.addQuestion(q17, TABLE_GENERAL);    this.addQuestion(q18, TABLE_GENERAL);
        this.addQuestion(q19, TABLE_GENERAL);    this.addQuestion(q20, TABLE_GENERAL);
        this.addQuestion(q21, TABLE_GENERAL);    this.addQuestion(q22, TABLE_GENERAL);
        this.addQuestion(q23, TABLE_GENERAL);    this.addQuestion(q24, TABLE_GENERAL);
        this.addQuestion(q25, TABLE_GENERAL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_GENERAL);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_ZOBIAWA);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_IMAGE);

        //CREATE TABLES AGAIN
        onCreate(db);
    }

    public void addQuestion(Question question, String TableName){
        // SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_QUESTION, question.getQUESTION());
        values.put(KEY_ANSWER, question.getANSWER());
        values.put(KEY_OPTA, question.getOPTA());
        values.put(KEY_OPTB, question.getOPTB());
        values.put(KEY_OPTC, question.getOPTC());
        values.put(KEY_OPTD, question.getOPTD());

        // insert row
        dbase.insert(TableName, null, values);
    }


    public List<Question> getAllQuestions(String TableName) {
        List<Question> questionList = new ArrayList<Question>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TableName;
        dbase = this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question question = new Question();
                question.setID(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                question.setQUESTION(cursor.getString(cursor.getColumnIndex(KEY_QUESTION)));
                question.setANSWER(cursor.getString(cursor.getColumnIndex(KEY_ANSWER)));
                question.setOPTA(cursor.getString(cursor.getColumnIndex(KEY_OPTA)));
                question.setOPTB(cursor.getString(cursor.getColumnIndex(KEY_OPTB)));
                question.setOPTC(cursor.getString(cursor.getColumnIndex(KEY_OPTC)));
                question.setOPTD(cursor.getString(cursor.getColumnIndex(KEY_OPTD)));

                questionList.add(question);
            } while (cursor.moveToNext());
        }
        //shuffle list of questions
        Collections.shuffle(questionList);

        return questionList; // return list of questions
    }
}
