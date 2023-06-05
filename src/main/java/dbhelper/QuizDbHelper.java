package dbhelper;

import static android.app.DownloadManager.COLUMN_ID;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.xh229050532.Asian_Game_Knowledge.App;

import java.util.ArrayList;
import java.util.List;

import database.QuizContract;

//实现数据库操作,从数据库中查询问题并动态的生成问题展示列表
public class QuizDbHelper extends SQLiteOpenHelper {

    // 数据库名和版本
    private static final String DATABASE_NAME = "Quiz.db";
    private static final int DATABASE_VERSION = 1;

    // 表名和列名常量
    private static final String TABLE_NAME = "Questions";
    private static final String COLUMN_QUESTION = "Question";
    private static final String COLUMN_OPTION1 = "Option1";
    private static final String COLUMN_OPTION2 = "Option2";
    private static final String COLUMN_OPTION3 = "Option3";
    private static final String COLUMN_ANSWER_NR = "AnswerNr";

    private SQLiteDatabase db;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        // 创建SQL语句
        String CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_QUESTION + " TEXT," +
                COLUMN_OPTION1 + " TEXT," +
                COLUMN_OPTION2 + " TEXT," +
                COLUMN_OPTION3 + " TEXT," +
                COLUMN_ANSWER_NR + " INTEGER" +
                ")";
        db.execSQL(CREATE_QUESTIONS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 删除原来的表
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // 添加问题到数据库
    public void addQuestion(App question) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_QUESTION, question.getQuestion());
        cv.put(COLUMN_OPTION1, question.getOption1());
        cv.put(COLUMN_OPTION2, question.getOption2());
        cv.put(COLUMN_OPTION3, question.getOption3());
        cv.put(COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(TABLE_NAME, null, cv);
    }

    public List<App> getAllQuestions() {
        return null;
    }

    public void fillQuestionsTable() {
    }
}
