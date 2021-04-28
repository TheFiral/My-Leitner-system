package en.engilish.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import en.engilish.R;
import en.engilish.dao.WordsRepo;
import en.engilish.entity.AnotherWord;


public class WordAdapter extends ArrayAdapter<AnotherWord> {
    private LayoutInflater inflater;
    private int layout;
    private ArrayList<AnotherWord> wordsList;

    WordAdapter(Context context, int resource, ArrayList<AnotherWord> words){
        super(context,resource,words);
        this.wordsList = words;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent){

        final ViewHolder viewHolder;
        if(convertView == null){
            convertView = inflater.inflate(this.layout, parent,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final AnotherWord word = wordsList.get(position);

        viewHolder.englishView.setText(word.getEnglishTranslate());
        viewHolder.russianView.setText(word.getRussianTranslate());
        viewHolder.countView.setText(Integer.toString(word.getCount()));

        viewHolder.insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WordsRepo db = word.getRepo();
                db.insertWord(word.getEnglishTranslate(),word.getRussianTranslate(),0);
                db.deletedWord(word);
            }
        });
        viewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WordsRepo db = word.getRepo();
                db.deletedWord(word);
            }
        });

        return convertView;
    }

    private class ViewHolder{
        final Button deleteButton, insertButton;
        final TextView englishView, russianView, countView;

        ViewHolder(View view){
            deleteButton = view.findViewById(R.id.deleteButton);
            insertButton = view.findViewById(R.id.insertButton);
            englishView = view.findViewById(R.id.textViewEnglish);
            russianView = view.findViewById(R.id.textViewRussian);
            countView = view.findViewById(R.id.textViewCount);
        }
    }
}
