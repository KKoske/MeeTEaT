
・プロジェクト概要

本プロジェクト「MeeTEaT」は、飲食店のテイクアウトのニーズを補助するものです。ユーザーと飲食店の購入プロセスをシームレスに繋ぐことで、スムーズなテイクアウト購入体験を提供するアプリケーションサービスとなっております。これは私の４度目の起業で、リーンや顧客開発モデルなどのフレームワークを用いた事業構築を行いました。

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

3. APIキーの代入について(セキュリティとして、意図的にAPIキーをソースコードから消し ています。必要であれば伝えて頂ければと思います。)

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
ニーズを満たすというより、すでにアプリを使おうとしているユーザーがいる場合、という目線から便利になる工夫をしています。

### 1.　横3店舗分の画像が表示されるように設計

　　　←右が3画像(今)　　左が2.5画像の場合→

<p>
  <img src="https://raw.githubusercontent.com/KKoske/MeeTEaT/8302db2992061427ed0b5de57e7f2b58d88787d5/3.gif" width="200">
  <img src="https://raw.githubusercontent.com/KKoske/MeeTEaT/7d9ed4318bed95ebfa48c2149bc50ef0deb81667/2.gif" width="200">
</p>

- 効果

違いは、横に表示される画像が2.5画像か3画像かです。この違いの目的は１目でスキャンできる情報が多いかどうかで、食事購入における情報の取得を素早く行わせようとしています。
拠点でゆっくり注文するUberEatsが横2.5画像というUIを採択している、ここをベースに3画像が多めの情報であり、画面を引き目にスキャンできるというふうに色々試行錯誤して決めました。
<dev>
 
### 2.グリッドのはみ出し画像が上から下へと、大きくはみ出す設計

　　　←右が上から順に大きくなる（今）　　左が全て同じの場合→

   <p>
  <img src="https://raw.githubusercontent.com/KKoske/MeeTEaT/7d9ed4318bed95ebfa48c2149bc50ef0deb81667/Before.gif" width="200">
  <img src="https://raw.githubusercontent.com/KKoske/MeeTEaT/7d9ed4318bed95ebfa48c2149bc50ef0deb81667/after.gif" width="200">
</p>

- 効果

違いは、グリッド右端部分に表示される、次の画像のはみ出し部分で、上の部分の上が少なく下のはみ出しが大きく見えるように設計しました。簡単に表現すると、商品選びやスキャンに集中している時に邪魔なはみ出しは見えないようした、ということ。食べたい食事に向き合う際に無意識に見えてしまう上段のはみ出しをほぼ見えないようにして、次の商品があるという案内が欲しい下段のみにはみ出しを表示させることで、スキャンに集中してる際には邪魔しないのに、最後の欲しい時だけそっと気づくような案内を実現させようとした設計です。
<dev>
 
### 3.ルート上店舗だけ、グリッドを3x3にし、店舗表示を多く見えるよう設計

　　　←3x3グリッド（今）　　左が3x1→

   <p>
  <img src="https://raw.githubusercontent.com/KKoske/MeeTEaT/7d9ed4318bed95ebfa48c2149bc50ef0deb81667/3x3.gif" width="200">
  <img src="https://raw.githubusercontent.com/KKoske/MeeTEaT/7d9ed4318bed95ebfa48c2149bc50ef0deb81667/1x3.gif" width="200">
</p>

- 効果
違いは一番上にある「ルート上の店舗」の表示店舗数の違いです。この目的は購入意思決定のスピード効率化で、「結局ここの商品買うんやから、先に多めに見せといてあげるわ」という事で、購入意思の促進になり,また決定を早める目的もある。 スキャンの時いいと思ったものと、購入実現度が高いもの合致がいくのを補助し結果、食べたいと思ったものを買えることになることを想定しています。


## 今後の開発目標

- **アーキテクチャの最適化**  
  - MVVM（Model-View-ViewModel）を採用し、UI とデータの責務を分離。  
  - 保守性・テスト容易性を向上し、拡張しやすい設計を実現。

- **UI のモダナイズ**  
  - XML ベースの UI から Jetpack Compose へ移行し、宣言的 UI フレームワークを活用。  
  - コードの簡潔化、リアクティブな UI 管理、開発効率の向上を実現。

- **データ管理の最適化**  
  - SQLite から Room、Firebase Firestore、SQL を用途に応じて採用し、柔軟なデータ管理を実現。  
  - オフライン対応やリアルタイムデータ同期を考慮。

- **品質保証と安定性向上**  
  - ユニットテスト（JUnit）の導入や、操作によるクラッシュが出ないようテストし、コードの品質を担保。  
  - 予期しないクラッシュを防ぎ、堅牢なアプリ設計を実現。

- **運用・保守性の強化**  
  - クラス・メソッドの責務を明確化し、拡張しやすいコード構造を維持。  
  - スレッド管理・非同期処理を考慮し、効率的な API 設計を実現。















## デモ版操作の注意点 

画像遷移のポイントとなる部分には色をつけておりますが、店舗・商品表示画面でタップできるポイントは構造上まだ色を付けれていないため、ここにて記載します。

1. 店舗一覧画面では[ルート上の店舗]カードのドミノピザの画像をタップすることで 遷移します。[ルート上の店舗]カード以外の画像をタップしても、データベースから 店舗画像の照合で合致した商品の一覧表示になるのですが、ドミノピザ以外の商品デ ーターが入っていませんので、何も表示されないページに遷移されます。

2. グリッドのはみ出しは、グリッドの部分を1画像くらいスワイプして指を離すと、 はみ出しが表示される様になります。

3. ドミノピザの商品ページに辿り着いたら、ハロウィンボルケーノ&ハロウィンブラ ックチキンを選択してください、他の画像はタップしても遷移はしないですので注意 して下さい。

4. 実装・調整中ですので、適切な表示がされていない箇所などについてはお容赦いた だきたく存じます。

<br><br><br>

補足: なぜ商品画像にハロウィン要素があるのかと言うと、私がこのアプリを開発していた時 期がハロウィンシーズンだった為です。開発に専念してハロウィンイベントに参加でき なかったため、少しでもその雰囲気を楽しむために取り入れました。 皆さんもぜひ楽 しんで下さい!

<br><br>
また、このREADMEから興味を持った、一緒にこの事業をでかくしたい。という方がいましたらぜひ連絡頂ければと思います。
tombrian033@gmail.com



