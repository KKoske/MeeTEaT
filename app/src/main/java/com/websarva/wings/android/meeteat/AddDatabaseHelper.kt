package com.websarva.wings.android.meeteat

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class AddDatabaseHelper(private val context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    //class AddDatabaseHelper(private val context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
        // すべての他のコードはそのまま





    companion object {
        private const val DATABASE_NAME = "MeeTEaT.db"
        private const val DATABASE_VERSION = 1
    }


    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS Store (
                id TEXT PRIMARY KEY,
                name TEXT NOT NULL,
                image_url TEXT,
                address TEXT,
                hours TEXT ,
                preparation_time INTEGER 
                
            )
        """.trimIndent()
        )

        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS Item (
            id INTEGER PRIMARY KEY,
            store_id TEXT NOT NULL,
            name TEXT NOT NULL,
            image_url TEXT,
            price REAL,
            description TEXT,
            preparation_time INTEGER,  -- 提供時間（分単位で保存）
            FOREIGN KEY (store_id) REFERENCES Store(id)
            )
        """.trimIndent()

        )
        try {
            Log.d("HomeScreenActivity", "開始しました")
            // データベース操作や UI 初期化
        } catch (e: Exception) {
            Log.e("HomeScreenActivity", "例外が発生しました: ${e.message}", e)
        }

        insertSampleData(db)
        Log.d("InsertSampleData", "サンプルデータが挿入されました")


    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS Store_Data")
        db.execSQL("DROP TABLE IF EXISTS Item_Data")
        onCreate(db)
    }

    private fun insertSampleData(db: SQLiteDatabase) {
        db.execSQL("INSERT INTO Store (id, name, image_url, address, hours, preparation_time) VALUES('1', '王将　ジョイフル店','img_ousho','新宿駅前','10:00~23:00','15')")

        db.execSQL("INSERT INTO Store (id, name, image_url, address, hours, preparation_time) VALUES('2', '餃子バー「ジャオズ」','img_jyaoz','新宿駅前','17:00~3:00','10')")

        db.execSQL("INSERT INTO Store (id, name, image_url, address, hours, preparation_time) VALUES('3', '「田燕」','img_denen','新宿駅前','11:00~20:00','20')")

        db.execSQL("INSERT INTO Store (id, name, image_url, address, hours, preparation_time) VALUES('4', '中華屋「竜の子」','img_ryunoko','新宿駅前','10:00~20:00','20')")

        db.execSQL("INSERT INTO Store (id, name, image_url, address, hours, preparation_time) VALUES('5', '吉野家','img_restaurant_Yoshinoya','新宿駅前','7:00~2:00','5')")

        db.execSQL("INSERT INTO Store (id, name, image_url, address, hours, preparation_time) VALUES('6', 'CoCo壱番屋 ','img_restaurant_CoCoichi','新宿駅前','11:00~23:30','10')")

        db.execSQL("INSERT INTO Store (id, name, image_url, address, hours, preparation_time) VALUES('7', '串カツ串結','img_kushimusubi','新宿駅前','17:00~23:00','20')")

        db.execSQL("INSERT INTO Store (id, name, image_url, address, hours, preparation_time) VALUES('8', '納豆「関東屋」','img_nattou','新宿駅前','12:00~20:00','15')")

        db.execSQL("INSERT INTO Store (id, name, image_url, address, hours, preparation_time) VALUES('9', 'やこ焼きバー ええやん」','img_takoyakibar','新宿駅前','18:00~5:00','30')")

        db.execSQL("INSERT INTO Store (id, name, image_url, address, hours, preparation_time) VALUES('10', '焼鳥「山陽堂」','img_sanyoudo','新宿駅前','16:30~0:00','30')")

        db.execSQL("INSERT INTO Store (id, name, image_url, address, hours, preparation_time) VALUES('11', 'パスタアルバ','img_alba','新宿駅前','11:00~20:00','30')")

        db.execSQL("INSERT INTO Store (id, name, image_url, address, hours, preparation_time) VALUES('12', 'ドンジョバンニ','img_dongiovanni','新宿駅前','13:00~22:00','30')")

        db.execSQL("INSERT INTO Store (id, name, image_url, address, hours, preparation_time) VALUES('13', 'ドトール','img_doutal','新宿駅前','10:00~22:30','30')")

        db.execSQL("INSERT INTO Store (id, name, image_url, address, hours, preparation_time) VALUES('14', 'タリーズ','img_tullys','新宿駅前','10:00~22:00','30')")

        db.execSQL("INSERT INTO Store (id, name, image_url, address, hours, preparation_time) VALUES('15', 'マクドナルド','img_macdonalds','新宿駅前','5:00~24:00','30')")

        db.execSQL("INSERT INTO Store (id, name, image_url, address, hours, preparation_time) VALUES('16', 'ドミノピザ','img_dominopizza','新宿駅前','10:00~5:00','30')")

        db.execSQL("INSERT INTO Store (id, name, image_url, address, hours, preparation_time) VALUES('17', 'Vege+','img_vege','新宿駅前','10:00~5:00','30')")

        //ドミノピザ　メニュー一覧　オファーカード
        db.execSQL("INSERT INTO Item (id, store_id, name, image_url, price, description, preparation_time) VALUES('1', '16', 'たーっぷりアメリカン　MORE AMERICAN', 'img_domino_offer_1', '¥2180', 'ペパロニ、チーズがアメリカンピザの1.5倍量！', '10')")

        db.execSQL("INSERT INTO Item (id, store_id, name, image_url, price, description, preparation_time) VALUES('2', '16', 'ジェノベーゼ　Genovese', 'img_domino_offer_2', '¥2570', 'パスタソースでお馴染みのジェノベーゼをアレンジしてピザにしました。ガーリックが程よく効いているので、最後まで飽きずに食べられます。イタリアンな気分の時にはコレで決まり！', '10')")

        db.execSQL("INSERT INTO Item (id, store_id, name, image_url, price, description, preparation_time) VALUES('3', '16', 'クワトロ・2ハッピー Quattro 2Happy', 'img_domino_offer_3', '¥2810', '1.マヨシュリンプ2.マルゲリータ3.ドミノ・デラックス4.炭火焼きチキテリの組み合わせ。', '10')")

        db.execSQL("INSERT INTO Item (id, store_id, name, image_url, price, description, preparation_time) VALUES('4', '16', 'ダブルモッツァレラ DoubleMozzarella', 'img_domino_offer_4', '¥2330', 'モッツァレラチーズ（ダブル）、トマトソース。', '10')")

        db.execSQL("INSERT INTO Item (id, store_id, name, image_url, price, description, preparation_time) VALUES('5', '16', 'マヨシュリンプ Mayo Shrimp', 'img_domino_offer_5', '¥2790', 'トマトソース、エビ、ピーマン、コーン、マヨソース', '10')")

        db.execSQL("INSERT INTO Item (id, store_id, name, image_url, price, description, preparation_time) VALUES('6', '16', 'ガーリックマスター', 'img_domino_offer_6', '¥2640', 'ガーリックたっぷり。香ばしさもたっぷり。粗びきソーセージもたっぷり。うまさもたっぷり入ってます。体に良い有効成分もたっぷり。元気出ちゃいますよ。', '10')")

        //ドミノピザ　メニュー一覧　レコメンドカード
        db.execSQL("INSERT INTO Item (id, store_id, name, image_url, price, description, preparation_time) VALUES('7', '16', 'のびーるチーズ棒 Cheese Stick', 'img_domino_recommend_3', '¥599', 'のび〜る濃厚モッツァレラチーズ100%を、独自配合したハーブを使用したサクサクの衣で包んだ止まらないおいしさのチーズ棒が再登場！ディップ用トマトソース付き。', '10')")

        db.execSQL("INSERT INTO Item (id, store_id, name, image_url, price, description, preparation_time) VALUES('8', '16', 'ポテナゲ(ポテト＆ナゲット) Pote-Nage(Potato&Nuggets)', 'img_domino_recommend_2', '¥600', '大人気のサイドメニュー、パーティにもぴったりのお得なセットです。チキンナゲット４ピースとポテトフライの組み合わせ。', '10')")

        db.execSQL("INSERT INTO Item (id, store_id, name, image_url, price, description, preparation_time) VALUES('9', '16', 'ギガ・ミート Giga Meat', 'img_domino_recommend_1', '¥2740', 'ボリューム満点おいしさギガクラスのミートミックス、もりもり食べて、みんな大満足。', '10')")

        db.execSQL("INSERT INTO Item (id, store_id, name, image_url, price, description, preparation_time) VALUES('10', '16', 'クワトロ・ハッピー Quattro Happy', 'img_domino_recommend_5', '¥2380', '人気の定番メニューを気軽に楽しむならコレ！大人も子どもも満足できる１枚です。', '10')")

        db.execSQL("INSERT INTO Item (id, store_id, name, image_url, price, description, preparation_time) VALUES('11', '16', 'マルゲリータ Mergherita', 'img_domino_recommend_6', '¥1980', 'イタリア産ボッコンチーニ、バジルソース（ハーフ）、バジル、チェリートマト、トマトソース（ハーフ）', '10')")

        db.execSQL("INSERT INTO Item (id, store_id, name, image_url, price, description, preparation_time) VALUES('12', '16', 'アメリカン AMERICAN', 'img_domino_recommend_4', '¥1580', 'アメリカ人のピザの楽しみ方はシンプル・イズベスト。もちろんドミノ＿ピザの原点も、とてもシンプル。アメリカンなら、これです。', '10')")

        //ドミノピザ　メニュー一覧　ハロウィンカード
        db.execSQL("INSERT INTO Item (id, store_id, name, image_url, price, description, preparation_time) VALUES('13', '16', 'ハロウィンボルケーノ&ハロウィンブラックチキン Halloween Volvano & Halloween BlackChicken', 'img_domino_halloween_2', '¥4990', 'ハロウィンボルケーノとハロウィンブラックチキンのセット。*特製棺桶BOXは数量限定となります。店舗在庫が終了次第、通常サイドメニューBOXでお届けします。', '10')")

        db.execSQL("INSERT INTO Item (id, store_id, name, image_url, price, description, preparation_time) VALUES('15', '16', 'ハロウィンボルケーノ Halloween Volvano', 'img_domino_halloween_3', '¥4440', 'まるで火山のようなたっぷり濃厚チェダーチーズソースでお好きにディップしてお楽しみください！', '10')")

        db.execSQL("INSERT INTO Item (id, store_id, name, image_url, price, description, preparation_time) VALUES('14', '16', 'ハロウィンブラックチキン Halloween BlackChicken', 'img_domino_halloween_1', '¥590', '竹炭を使用した真っ黒なチキン。下味に９種のスパイス、衣にも黒胡椒を使用しており、程よいスパイス感と風味を感じれるオリジナルチキンです。*特製棺桶BOXは数量限定となります。店舗在庫が終了次第、通常サイドメニューBOXでお届けします。', '10')")

        db.execSQL("INSERT INTO Item (id, store_id, name, image_url, price, description, preparation_time) VALUES('16', '16', 'チーズボルケーノ ポテナゲ (ポテト&ナゲット) Cheese Volcano Pote-Nage(Potato & Nuggets)', 'img_domino_halloween_4', '¥1290', 'たっぷり濃厚チェダーチーズソースをポテトフライとチキンナゲットと一緒にお楽しみください！チェーダーチーズソース、ポテトフライ、チキンナゲット(8ピース)の組み合わせ。', '10')")

        db.execSQL("INSERT INTO Item (id, store_id, name, image_url, price, description, preparation_time) VALUES('17', '16', 'チーズボルケーノ ディップソース Cheese Volcano Dip Sause', 'img_domino_halloween_5', '¥799', 'たっぷり濃厚チェダーチーズソースをお好きなぷ座やサイドメニューと一緒にお楽しみください！', '10')")

        //ドミノピザ　メニュー一覧　セットカード
        db.execSQL("INSERT INTO Item (id, store_id, name, image_url, price, description, preparation_time) VALUES('18', '16', 'Sサイズピザ３枚+サイド2個セット 3S-size Pizzas + 2Sides Set', 'img_domino_set_2', '¥4799', 'お好きなSピザ３枚+お好きなサイドメニュー2個', '10')")

        db.execSQL("INSERT INTO Item (id, store_id, name, image_url, price, description, preparation_time) VALUES('19', '16', 'Sサイズピザ2枚+サイド2個セット 2S-size Pizzas + 2Sides Set', 'img_domino_set_4', '¥3599', 'お好きなSピザ2枚+お好きなサイドメニュー2個', '10')")

        db.execSQL("INSERT INTO Item (id, store_id, name, image_url, price, description, preparation_time) VALUES('20', '16', 'Sサイズピザ1枚+サイド1個セット 1S-size Pizzas + 1Sides Set', 'img_domino_set_3', '¥1899', 'お好きなSピザ1枚+お好きなサイドメニュー1個', '10')")

        db.execSQL("INSERT INTO Item (id, store_id, name, image_url, price, description, preparation_time) VALUES('21', '16', 'Sサイズピザ３枚 3S-size Pizzas', 'img_domino_set_5', '¥4199', 'お好きなSピザ3枚', '10')")

        db.execSQL("INSERT INTO Item (id, store_id, name, image_url, price, description, preparation_time) VALUES('22', '16', 'Sサイズピザ2枚 2S-size Pizzas', 'img_domino_set_6', '¥2899', 'お好きなSピザ2枚', '10')")

        db.execSQL("INSERT INTO Item (id, store_id, name, image_url, price, description, preparation_time) VALUES('23', '16', 'ドリンクセット Drink Set', 'img_domino_set_1', '¥599', 'お好きなドリンク1本+お好きなサイド1個', '10')")

        //ドミノピザ　メニュー一覧　バリューピザカード
        db.execSQL("INSERT INTO Item (id, store_id, name, image_url, price, description, preparation_time) VALUES('24', '16', 'ピザBENTO 高麗カルビ+お好きなサイド Pizza BENTO Giryeo Gallbi + Any Side', 'img_domino_valuepizza_2', '¥1290', 'おひとり様用ピザ(15cm)', '10')")

        db.execSQL("INSERT INTO Item (id, store_id, name, image_url, price, description, preparation_time) VALUES('25', '16', 'ピザBENTO ガーリックマスター+お好きなサイド Pizza BENTO Garlic Master + Any Side', 'img_domino_valuepizza_3', '¥1090', 'おひとり様用ピザ(15cm)', '10')")

        db.execSQL("INSERT INTO Item (id, store_id, name, image_url, price, description, preparation_time) VALUES('26', '16', 'ピザBENTO ドミノデラックス&マヨじゃが+お好きなサイド Pizza BENTO Dominos Delux & Mayo Jaga + Any Side', 'img_domino_valuepizza_4', '¥1140', 'おひとり様用ピザ(15cm)', '10')")

        db.execSQL("INSERT INTO Item (id, store_id, name, image_url, price, description, preparation_time) VALUES('27', '16', 'ピザBENTO マルゲリータ+お好きなサイド Pizza BENTO Margherita + Any Side', 'img_domino_valuepizza_5', '¥1190', 'おひとり様用ピザ(15cm)', '10')")

        db.execSQL("INSERT INTO Item (id, store_id, name, image_url, price, description, preparation_time) VALUES('28', '16', 'ピザBENTO ガーリックマスター&高麗カルビ+お好きなサイド Pizza BENTO Garlic Master & Giryeo Gallbi + Any Side', 'img_domino_valuepizza_6', '¥1190', 'おひとり様用ピザ(15cm)', '10')")

        db.execSQL("INSERT INTO Item (id, store_id, name, image_url, price, description, preparation_time) VALUES('30', '16', 'ピザBENTO ピザライスボウル ガーリックマスター&高麗カルビ+お好きなサイド Pizza Rice Bowl Garlic Master & Giryeo Gallbi + Any Side', 'img_domino_valuepizza_1', '¥1590', 'ピザライスボウル ガーリック・マスター&高麗カルビとお好きなサイド1個の組み合わせ。', '10')")
    }

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
                val imageUrl = cursor.getString(cursor.getColumnIndexOrThrow("image_url"))

                val images = when (id) {
                    //onRouteStoresカード（homescreeen filter）
                    1 -> listOf(
                        ImageItem(R.drawable.img_dominopizza, "ドミノピザ", "すぐ受け取り"),
                        ImageItem(R.drawable.img_restaurant_yoshinoya, "吉野家", "すぐ受け取り"),
                        ImageItem(R.drawable.img_ousho, "王将", "すぐ受け取り"),
                        ImageItem(R.drawable.img_alba, "パスタ「アルバ」", "すぐ受け取り"),
                        ImageItem(R.drawable.img_doutal_2, "ドトール", "すぐ受け取り"),
                        ImageItem(R.drawable.img_restaurant_cocoichi, "CoCo壱番屋", "すぐ受け取り"),
                        ImageItem(R.drawable.img_denen, "中華「田燕」", "すぐ受け取り"),
                        ImageItem(R.drawable.img_macdonalds, "マクドナルド", "すぐ受け取り"),
                        ImageItem(R.drawable.img_nattou, "納豆「関東屋」", "すぐ受け取り"),
                        ImageItem(R.drawable.img_tullys_2, "タリーズ", "すぐ受け取り"),
                        ImageItem(R.drawable.img_kushimusubi, "串カツ「串結び」", "すぐ受け取り"),
                        ImageItem(R.drawable.img_sanyodo, "焼鳥「山陽堂」", "すぐ受け取り"),
                        ImageItem(R.drawable.img_dongiovanni, "ビストロ ドン・ジョバンニ", "すぐ受け取り"),
                        ImageItem(R.drawable.img_takoyakibar, "たこ焼きバー", "すぐ受け取り"),
                        ImageItem(R.drawable.img_vege, "Vege+", "すぐ受け取り"),
                        ImageItem(R.drawable.img_ryunoko, "竜の子", "すぐ受け取り"),
                        ImageItem(R.drawable.img_jyaoz, "餃子バー「ジャおず」", "すぐ受け取り"),


                        )
                    //postOrderStores
                    2 -> listOf(
                        ImageItem(R.drawable.img_macdonalds, "マクドナルド", "すぐ受け取り"),
                        ImageItem(R.drawable.img_nattou, "納豆「関東屋」", "すぐ受け取り"),
                        ImageItem(R.drawable.img_takoyakibar, "たこ焼きバー", "すぐ受け取り"),
                        ImageItem(R.drawable.img_jyaoz, "餃子バー「ジャオズ」", "すぐ受け取り"),
                    )

                    3 -> listOf(
                        ImageItem(R.drawable.img_denen, "田燕", "すぐ受け取り"),
                        ImageItem(R.drawable.img_ousho, "王将", "すぐ受け取り"),
                        ImageItem(R.drawable.img_jyaoz, "ジャオズバー", "すぐ受け取り"),
                        ImageItem(R.drawable.img_ryunoko, "竜の子", "すぐ受け取り"),
                    )

                    4 -> listOf(
                        ImageItem(R.drawable.img_kushimusubi, "串結び", "すぐ受け取り"),
                        ImageItem(R.drawable.img_restaurant_cocoichi, "CoCo壱番屋", "すぐ受け取り"),
                        ImageItem(R.drawable.img_takoyakibar, "たこ焼きバー", "すぐ受け取り"),
                        ImageItem(R.drawable.img_nattou, "納豆「関東屋」", "すぐ受け取り"),
                        ImageItem(R.drawable.img_sanyodo, "焼鳥「山陽堂」", "すぐ受け取り"),
                        ImageItem(R.drawable.img_restaurant_yoshinoya, "吉野家", "すぐ受け取り"),
                    )

                    5 -> listOf(
                        ImageItem(R.drawable.img_alba, "パスタ「アルバ」", "すぐ受け取り"),
                        ImageItem(R.drawable.img_dongiovanni, "ビストロ ドン・ジョバンニ", "すぐ受け取り"),
                    )

                    6 -> listOf(
                        ImageItem(R.drawable.img_doutal_2, "ドトール", "すぐ受け取り"),
                        ImageItem(R.drawable.img_tullys_2, "タリーズ", "すぐ受け取り"),
                    )

                    7 -> listOf(
                        ImageItem(R.drawable.img_dominopizza, "ドミノピザ", "すぐ受け取り"),
                        ImageItem(R.drawable.img_macdonalds, "マクドナルド", "すぐ受け取り"),
                    )

                    8 -> listOf(
                        ImageItem(R.drawable.img_vege, "Vege+ ベジプラス", "すぐ受け取り"),
                    )

                    else -> emptyList()
                }

                // `Store`オブジェクトをリストに追加
                stores.add(Store(id, name, address, imageUrl, images))
            } while (cursor.moveToNext()) // 次の行に移動
        }
        // カーソルを閉じてリソースを解放
        cursor.close()
        db.close()
        return stores


    }


    fun getAllItems(storeId: Int): List<Product> {

        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Item WHERE store_id = ?", arrayOf(storeId.toString()))
        val items = mutableListOf<Product>()

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                val imageUrl = cursor.getString(cursor.getColumnIndexOrThrow("image_url"))
                val price = cursor.getDouble(cursor.getColumnIndexOrThrow("price")) // 価格を取得

                cursor.getInt(cursor.getColumnIndexOrThrow("preparation_time"))
                val resourceId = context.resources.getIdentifier(imageUrl, "drawable", context.packageName)

                items.add(
                    Product(
                        id = id,
                        storeId = storeId,
                        name = name,
                        image_url = imageUrl,
                        price = price,
                        description = "",
                        preparationTime = cursor.getInt(cursor.getColumnIndexOrThrow("preparation_time"))
                    )
                )
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return items

    }
    fun productsToStore(products: List<Product>, storeName: String, storeAddress: String): List<Store> {
        Log.d("productsToStore", "Received Products Size: ${products.size}")
        Log.d("productsToStore", "Products List: $products")

        // Product リストを Store 型に変換
        return listOf(
            Store(
                id = products.firstOrNull()?.storeId ?: 0, // 最初の商品の storeId を利用
                name = storeName, // 店舗名を設定
                address = storeAddress, // 店舗住所を設定
                image_url = "", // Storeのメイン画像がない場合は空文字列
                images = products.map { product ->
                    //resIdの確認ログの追加
                    val resId = product.getImageResId(context)
                    Log.d("productsToStore", "Product: ${product.name}, ImageResId: $resId")

                    ImageItem(
                        imageResId = product.getImageResId(context),
                        name = product.name,
                        subInfo = "¥${product.price.toInt()}" // 価格を整形
                    )
                },

            )
        )
    }

        }


