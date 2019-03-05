package com.omelchenkoaleks.healthyfood;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogoActivity extends AppCompatActivity {
    private static final String TAG = "LogoActivity";
    private List<Recipe> recipeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        new ParsingAllRecipe().execute();
    }

    class ParsingAllRecipe extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            String urlHome = "https://wowbodyslim.com/recipes";

            try {
                Document doc = Jsoup.connect(urlHome).get();
                Log.e(TAG, " " + doc);
                Elements els = doc.select("div[class=cab-content__body-container container]");
                String nextPage = els.select("li[class=next]>a").attr("href"); // /recipes?page=2
                int count = Integer.parseInt(nextPage.replace("/recipes?page=", ""));

                for (int i = 1; i < count; i++) {
                    String url = "https://wowbodyslim.com/recipes?page=" + i;
                    itemRecipes(url);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        private void itemRecipes(String url) {
            try {
                String imgRecipe, nameRecipe, linkPageRecipe;
                Document doc = Jsoup.connect(url).get();
                Elements els = doc.select("div[class=recipes-list]");
                for (Element el : els) {
                    imgRecipe = "https://wowbodyslim.com" + el.select("img").attr("src");
                    nameRecipe = el.select("div[class=recipes-list__card-content js-dot-disabled]").text();
                    linkPageRecipe = null;

                    Log.d(TAG, imgRecipe + " " + nameRecipe + " " + linkPageRecipe);

                    recipeList.add(new Recipe(imgRecipe, nameRecipe, linkPageRecipe));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
