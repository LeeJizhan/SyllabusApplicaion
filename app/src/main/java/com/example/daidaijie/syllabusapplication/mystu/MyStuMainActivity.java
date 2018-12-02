package com.example.daidaijie.syllabusapplication.mystu;

import android.os.Bundle;
import android.widget.TextView;

import com.example.daidaijie.syllabusapplication.R;
import com.example.daidaijie.syllabusapplication.base.BaseActivity;
import com.example.daidaijie.syllabusapplication.bean.Semester;
import com.example.daidaijie.syllabusapplication.bean.UserLogin;
import com.example.daidaijie.syllabusapplication.model.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * MyStu 主界面
 */
public class MyStuMainActivity extends BaseActivity {

    @BindView(R.id.tv_text)
    TextView tv_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        //获取当前用户的账号密码
        Realm mRealm = Realm.getDefaultInstance();
        UserLogin userLogin = mRealm.where(UserLogin.class).findFirst();
        String userName = userLogin.getUsername();
        String password = userLogin.getPassword();

        //获取当前学期
        String year = "";
        String season = "";
        RealmResults<Semester> semesters = mRealm.where(Semester.class).findAll();
        for (Semester semester : semesters) {
            if (semester.isCurrent() == true) {
                year = semester.getYearString();
                season = semester.getSeasonString();
            }
        }

        String text = year + ":  " + season;
        tv_text.setText(text);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_mystu_main;
    }
}
