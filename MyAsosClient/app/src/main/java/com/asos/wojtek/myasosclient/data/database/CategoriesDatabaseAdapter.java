package com.asos.wojtek.myasosclient.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.asos.wojtek.myasosclient.data.model.Categories;
import com.asos.wojtek.myasosclient.utils.GsonUtil;


/**
 * SQLite database wrapper saving and reading categories
 */
public class CategoriesDatabaseAdapter extends DatabaseAdapter {

    public static final String COL_ID = "_id";
    public static final String COL_CATEGORY_TYPE = "category_type";
    public static final String COL_DATA = "data";
    public static final String RECENTLY_VIEWED_TABLE = "recently_viewed_table";

    public CategoriesDatabaseAdapter(Context context) {
        super(context);
        open();
    }


    public void delete(String categoriesType) {
        String[] delete = new String[]{categoriesType};
        getDatabase().delete(RECENTLY_VIEWED_TABLE, COL_CATEGORY_TYPE + " = ?", delete);
    }

    public void deleteAllCategories() {
        getDatabase().delete(RECENTLY_VIEWED_TABLE, null, null);
    }

    public long insert(String categoryType, Categories categoriesModel) {

        ContentValues args = new ContentValues();
        args.put(COL_CATEGORY_TYPE, categoryType);
        args.put(COL_DATA, GsonUtil.serialize(categoriesModel).getBytes());
        return getDatabase().insert(RECENTLY_VIEWED_TABLE, null, args);
    }

    public Categories getCategory(String categoryType) {
        Categories trackParcelResponse;
        String[] columns = new String[]{COL_ID, COL_CATEGORY_TYPE, COL_DATA};
        String[] select = new String[]{ categoryType};
        Cursor cursor = getDatabase().query(RECENTLY_VIEWED_TABLE, columns,COL_CATEGORY_TYPE + " = ?", select, null, null, null, "1");

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            byte[] blob = cursor.getBlob(cursor.getColumnIndex(COL_DATA));
            String json = new String(blob);
            trackParcelResponse = GsonUtil.deserialize(json, Categories.class);
            cursor.close();
            return trackParcelResponse;
        }

        cursor.close();
        return null;
    }



}