package com.asos.wojtek.myasosclient.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

/**
 * SQLite database for saving wishlist or bag type of items
 */
public class SavedItemsDatabaseAdapter extends DatabaseAdapter {

    public static final String COL_ID = "_id";
    public static final String COL_PRODUCT_ID = "product_id";
    public static final String COL_PRODUCT_QUANTITY = "product_quantity";
    public static final String SAVED_ITEMS_TABLE = "saved_items_table";
    //bag or wishlist
    public static final String COL_ITEM_TYPE = "item_type";

    public SavedItemsDatabaseAdapter(Context context) {
        super(context);
        open();
    }


    /**
     * Delete a product from the shopping bag
     * @param productId
     */
    private void delete(String productId) {
        String[] delete = new String[]{productId};
        getDatabase().delete(SAVED_ITEMS_TABLE, COL_PRODUCT_ID + " = ?", delete);
    }

    /**
     * Add a product to the shopping bag
     * @param productId - the id of the product to be added to the bag
     * @return row number if successful, -1 if error
     */
    public long addToSavedItem(String productId, String savedItemType) {
        String[] columns = new String[]{COL_ID, COL_PRODUCT_ID, COL_PRODUCT_QUANTITY};
        String[] select = new String[]{productId};
        Cursor cursor = getDatabase().query(SAVED_ITEMS_TABLE, columns,  COL_PRODUCT_ID + " = ?", select, null, null, null, "1");
        int currentQuantiy = 0;
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            currentQuantiy = cursor.getInt(cursor.getColumnIndex(COL_PRODUCT_QUANTITY));
            delete(productId);
            cursor.moveToNext();
        }

        cursor.close();
        ContentValues args = new ContentValues();
        args.put(COL_PRODUCT_ID, productId);
        args.put(COL_PRODUCT_QUANTITY, ++currentQuantiy);
        args.put(COL_ITEM_TYPE, savedItemType);
        return getDatabase().insert(SAVED_ITEMS_TABLE, null, args);

    }

    /**
     * retrieve the number of the items that are currently in the shopping bag
     * @return current bag size
     */
    public int getSavedItemsSize(String itemType) {
        int totalItems = 0;
        String[] columns = new String[]{COL_ID, COL_PRODUCT_ID, COL_PRODUCT_QUANTITY};
        String[] select = new String[]{ itemType};
        Cursor cursor = getDatabase().query(SAVED_ITEMS_TABLE, columns, COL_ITEM_TYPE + " = ?", select, null, null, null, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            int amount = cursor.getInt(cursor.getColumnIndex(COL_PRODUCT_QUANTITY));
            totalItems = totalItems + amount;
            cursor.moveToNext();
        }

        cursor.close();
        return  totalItems;
    }

    /**
     * clear the shopping bag of all the items
     */
    public void clearBag(){
       getDatabase().execSQL("delete from "+ SAVED_ITEMS_TABLE);
    }


}