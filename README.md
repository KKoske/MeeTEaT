MEETEAT -モバイルテイクアウト補助アプリ-

・プロジェクト概要
MEETEATは、飲食店のテイクアウト需要を支援するために作成されたモバイルアプリです。  
飲食店と顧客をシームレスにつなぐことで、スムーズな注文体験を提供し、飲食店の売上向上をサポートします。

主な機能:（実装済み機能のみここに記載）
- 飲食店の一覧表示
- 飲食店の商品一覧の表示
- ユーザーフレンドリーなUI/UX設計
- アプリの流れを簡易的に体験できる画面遷移

使用技術:
- 言語:Kotlin
- データベース: SQLite
- プラットフォーム: Android
- デザイン: XML
- バージョン管理: GitHub

・セットアップ手順
1:　リポジトリのクローン
```bash
   git clone https://github.com/your-username/meeteat.git
```
2:　AndroidSStudioでプロジェクトを開く

3:　プロジェクトをビルド

4: エミュレーターまたは実機で実行
- 推奨設定:APIレベル33(Android 13.0"Tiramisu"| arm64)
- 実機の場合はUSBデバッグを有効にしてください。

・主な実装
飲食店一覧とメニューの表示
- 目的: 飲食店情報や商品メニューを見やすく表示し、横スクロールによるスムーズな操作性の提供。
- 主なポイント:
  - RecyclerViewのネスト: 飲食店・商品画像をカード形式で表示し、横スクロールで閲覧可能に。
  - グリッドレイアウトの導入: 特定のカード機能には3x3のグリッドを採用し、複数の商品を一覧表示。
  - ページスクロール機能: グリッド内の画像はスワイプ操作でページ遷移を実装。
  - 画像はみ出しレイアウトの採用: グリッドにおける、画像のはみ出し表示の実装。
  - 工夫:
    - ２つのRecycleViewをベースに、複数のカード、画像の表示を仕組み化。

SQLiteデータベースの操作
- 目的:　店舗や商品のデータを管理・取得。
- 主なポイント:
  - SQLiteを使用して飲食店と商品データを保存。
  - データ取得用のメソッドなどを実装。

・デモ版操作の注意点

- 画像遷移のポイントとなる部分には色をつけておりますが、店舗・商品表示画面でタップできるポイントは構造上まだ色を付けれていないため、ここにて記載します。
1: 店舗一覧画面では[ルート上の店舗]カードのドミノピザの画像をタップすることで遷移します。[ルート上の店舗]カード以外の画像をタップしても、データベースから店舗画像の照合で合致した商品の一覧表示になるのですが、ドミノピザ以外の商品データーが入っていませ
んので、何も表示されないページに遷移されます。

2:グリッドのはみ出しは、グリッドの部分を１画像くらいスワイプして指を離すと、はみ出しが表示される様になります。

3:ドミノピザの商品ページに辿り着いたら、ハロウィンボルケーノ&ハロウィンブラックチキンを選択してください、他の画像はタップしても遷移はしないですので注意して下さい。

4:実装・調整中ですので、適切な表示がされていない箇所などについてはお容赦いただきたく存じます。

補足
なぜ商品画像にハロウィン要素があるのかと言うと、私がこのアプリを開発していた時期がハロウィンシーズンだった為です。開発に専念してハロウィンイベントに参加できなかったため、少しでもその雰囲気を楽しむために取り入れました。
皆さんもお楽しみいただければ幸いです。

