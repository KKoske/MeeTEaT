
・プロジェクト概要

本プロジェクト「MeeTEaT」は、飲食店のテイクアウトのニーズを補助するものです。ユーザーと飲食店の購入プロセスをシームレスに繋ぐことで、スムーズなテイクアウト購入体験を提供するアプリケーションサービスとなっております。これは私の４度目の起業で、リーンや顧客開発モデルなどの思考を用いた事業構築を行いました。

- 背景

元々は私が学校帰りに夕食としてスーパーのお惣菜を購入していたルーティンだったのですが、弁当などが売り切れで無駄足になることが多く、これ何とかならないかと思った経験から作ったサービスです。前身は、スーパー惣菜の在庫確認がリアルタイムで出来て、それをオンライン上で即購入ができるというサービスでした。後に、スーパーマーケットやユーザーへのインタビューなどのフィードバックから、方向転換をして今の飲食店向けテイクアウトになった背景があります。

・本デモアプリの操作方法

<img src="https://raw.githubusercontent.com/KKoske/MeeTEaT/main/MeeTEaT3MB.gif" width="300"> 


1:赤い囲みをタップして進む。

2:「ドミノピザ」をクリック！

3:「ハロウィンボルケーノ&ハロウィンブラックチキン」をクリック！


・このアプリで何ができるのか

本アプリは自宅周辺における飲食店を表示し、テイクアウトを予約注文することが可能なサービスです。
- アプリとしての流れ

メイン画面では現在地や自宅周辺の飲食店が表示されています。選択した飲食店のメニュー商品をカートに入れ、購入することができます。購入確定後は店舗に商品を受け取りに行き、一連の取引が完了する流れになります。　　



<br><br><br>


# 技術README

## 主な機能:(実装済み機能のみここに記載)
- 飲食店の一覧表示
- 飲食店の商品一覧の表示
- ユーザーフレンドリーなUI/UX設計
- アプリの流れを簡易的に体験できる画面遷移
- 現在地と設定地点のルートを表示するMAP機能
## 使用技術:
- 言語:Kotlin
- データベース: SQLite
- プラットフォーム: Android
- デザイン: XML
- バージョン管理: GitHub
- API: Maps SDK for Android, Directions API
## セットアップ手順

1. リポジトリのクローン

git clone https://github.com/your-username/meeteat.git

2. AndroidStudioでプロジェクトを開く

3. APIキーの代入(セキュリティとして、意図的にAPIキーをソースコードから消し ています)

 - Manifest.xmlのappilication,meta-dataよりvalueをAPIキーに変更 「AIzaSyAPlV009oGel-WxDKv2rAOMCtsWOoZmsvo」

 - HomeScreenActivityファイルのDrawRoute()の「代入はここ」にAPIキーを代入

「AIzaSyBXVO3tIpmXySd5PxV71uPF-IadDEYPrBM」

4. プロジェクトをビルド

5. エミュレーターまたは実機で実行

 - 推奨設定:APIレベル33(Android 13.0"Tiramisu"| arm64)

 - 実機の場合はUSBデバッグを有効にしてください。


## 主な実装

### 「飲食店一覧とメニューの表示」

目的: 飲食店情報や商品メニューを見やすく表示し、横スクロールによるスムーズな操作性の提供。

主なポイント:
- RecyclerViewのネスト: 飲食店・商品画像をカード形式で表示し、横スクロールで 閲覧可能に。
- グリッドレイアウトの導入: 特定のカード機能には3x3のグリッドを採用し、複数の 商品を一覧表示。
- ページスクロール機能: グリッド内の画像はスワイプ操作でページ遷移を実装。 ・画像はみ出しレイアウトの採用: グリッドにおける、画像のはみ出し表示の実装。 ・APIの実装。現在地と指定地点のルートを表示させる。

工夫:2つのRecycleViewをベースに、複数のカード、画像の表示を仕組み化。


### 「SQLiteデータベースの操作」

目的: 店舗や商品のデータを管理・取得。

主なポイント: SQLiteを使用して飲食店と商品データを保存。 ・データ取得用のメソッドなどを実装。

工夫: 店舗一覧では静的な店舗画像データの取得・表示だったのに対して、商品一覧画面では店舗idと紐づいた動的な商品画像の取得・表示を実装した。

### 「MapAPIの表示」

目的: 現在地と設定地点のルートを表示させる。

主なポイント: Google Maps Platform のMaps SDK for Androidでマップの表示。 ・Directions APIで取得した現在地と設定地点のルート情報をマップに表示。


# UI/UXの工夫と効果
前提として仕事帰りに夕食を購入し家で食べる人で、シチュエーションとして最寄駅から歩いて家に帰る間にテイクアウトアプリで食事を探して購入するというイメージです。
### 1.　横3店舗分の画像が表示されるように設計

<p>
  <img src="https://raw.githubusercontent.com/KKoske/MeeTEaT/7d9ed4318bed95ebfa48c2149bc50ef0deb81667/2.gif" width="200"style="margin-right: 10;>
  <img src="https://raw.githubusercontent.com/KKoske/MeeTEaT/7d9ed4318bed95ebfa48c2149bc50ef0deb81667/3.gif" width="200">
</p>




## デモ版操作の注意点 

画像遷移のポイントとなる部分には色をつけておりますが、店舗・商品表示画面でタップできるポイントは構造上まだ色を付けれていないため、ここにて記載します。

1. 店舗一覧画面では[ルート上の店舗]カードのドミノピザの画像をタップすることで 遷移します。[ルート上の店舗]カード以外の画像をタップしても、データベースから 店舗画像の照合で合致した商品の一覧表示になるのですが、ドミノピザ以外の商品デ ーターが入っていませんので、何も表示されないページに遷移されます。

2. グリッドのはみ出しは、グリッドの部分を1画像くらいスワイプして指を離すと、 はみ出しが表示される様になります。

3. ドミノピザの商品ページに辿り着いたら、ハロウィンボルケーノ&ハロウィンブラ ックチキンを選択してください、他の画像はタップしても遷移はしないですので注意 して下さい。

4. 実装・調整中ですので、適切な表示がされていない箇所などについてはお容赦いた だきたく存じます。

<br><br><br>

補足: なぜ商品画像にハロウィン要素があるのかと言うと、私がこのアプリを開発していた時 期がハロウィンシーズンだった為です。開発に専念してハロウィンイベントに参加でき なかったため、少しでもその雰囲気を楽しむために取り入れました。 皆さんもぜひ楽 しんで下さい!



