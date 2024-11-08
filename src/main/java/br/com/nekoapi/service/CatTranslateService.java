/*
 * @author Marina Yumi Kanadani - RM 558404 - 1TDSPX
 */

package br.com.nekoapi.service;

import br.com.nekoapi.model.vo.CatVO;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import java.io.IOException;

public class CatTranslateService {

    private static final Translate translate = TranslateOptions.getDefaultInstance().getService();

    public static String translateText(String text, String targetLanguage) {
        Translation translation = translate.translate(text, Translate.TranslateOption.targetLanguage(targetLanguage));
        return translation.getTranslatedText();
    }

    public static CatVO[] searchAndTranslateCatBreed() throws IOException {
    	CatVO[] cats = CatService.searchCatBreed();

        if (cats != null) {
            for (CatVO cat : cats) {
                cat.setName(translateText(cat.getName(), "pt"));
                cat.setDescription(translateText(cat.getDescription(), "pt"));
            }
        }
        
        return cats;
    }    
}
