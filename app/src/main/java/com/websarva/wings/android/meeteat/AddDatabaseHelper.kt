package layout

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AddDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "MeeTEaT.db"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE IF NOT EXISTS Store (
                id TEXT PRIMARY KEY,
                name TEXT NOT NULL,
                address TEXT
            )
        """.trimIndent())
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS Store")
        onCreate(db)
    }

    data class Store(val id: Int, val name: String, val address: String)

    fun getAllStores(): List<Store> {
        // 読み込み用のデータベースインスタンスを取得
        val db = readableDatabase
        // `Store`テーブルの全データを取得するクエリを実行
        val cursor = db.rawQuery("SELECT * FROM Store", null)
        // データを格納するリスト
        val stores = mutableListOf<Store>()



        // 取得したデータが存在する場合、カーソルを使ってデータをリストに変換
        if (cursor.moveToFirst()) {
            do {
                // 各カラムからデータを取得してStoreオブジェクトを作成
                val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                val address = cursor.getString(cursor.getColumnIndexOrThrow("address"))
                // `Store`オブジェクトをリストに追加
                stores.add(Store(id, name, address))
            } while (cursor.moveToNext()) // 次の行に移動
        }


        // カーソルを閉じてリソースを解放
        cursor.close()
        return stores
    }

}
