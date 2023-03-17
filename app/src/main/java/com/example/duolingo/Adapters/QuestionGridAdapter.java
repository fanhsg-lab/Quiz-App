package com.example.duolingo.Adapters;

import static com.example.duolingo.DbQuery.ANSWERED;
import static com.example.duolingo.DbQuery.NOT_VISITED;
import static com.example.duolingo.DbQuery.REVIEW;
import static com.example.duolingo.DbQuery.UNANSWERED;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.duolingo.DbQuery;
import com.example.duolingo.QuestionsActivity;
import com.example.duolingo.R;

public class QuestionGridAdapter extends BaseAdapter {

    private int numOfQues;
    private Context context;


    public QuestionGridAdapter(Context context,int numOfQues ) {
        this.numOfQues = numOfQues;
        this.context = context;
    }

    @Override
    public int getCount() {
        return numOfQues;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        View myview;

        if(view == null)
        {

            myview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ques_grid_item, viewGroup, false);
        }
        else
        {
            myview = view;
        }

        myview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(context instanceof QuestionsActivity)
                    ((QuestionsActivity)context).goToQuestion(i);

            }
        });

        TextView quesTV = myview.findViewById(R.id.ques_num);
        quesTV.setText(String.valueOf(i+1));

        Log.d("colors",String.valueOf(DbQuery.g_quesList.get(i).getStatus()));
        switch (DbQuery.g_quesList.get(i).getStatus())
        {

            case  NOT_VISITED :
                quesTV.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(myview.getContext(),R.color.grey)));
                Log.d("colors0",String.valueOf(DbQuery.g_quesList.get(i).getStatus()));
                break;
            case  UNANSWERED:
                quesTV.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(myview.getContext(),R.color.red)));
                Log.d("colors1",String.valueOf(DbQuery.g_quesList.get(i).getStatus()));
                break;
            case  ANSWERED:
                quesTV.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(myview.getContext(),R.color.green)));
                Log.d("colors2",String.valueOf(DbQuery.g_quesList.get(i).getStatus()));
                break;
            case  REVIEW:
                quesTV.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(myview.getContext(),R.color.pink)));
                Log.d("colors3",String.valueOf(DbQuery.g_quesList.get(i).getStatus()));
                break;
            default:
                break;


        }

        return myview ;
    }
}
