# MeeTEaT - 飲食店テイクアウト予約アプリ

## アプリ概要
MeeTEaTは、飲食店のテイクアウト予約をスムーズに行えるアプリです。  
周辺の飲食店を検索し、メニューを確認・予約注文し、店舗で受け取ることができます。

## ユーザー目線: 何ができるアプリか
MeeTEaTでは、以下の流れでテイクアウト予約が可能です。

- 検索: 周辺の飲食店を検索し、設定した地点の飲食店を一覧表示  
- 予約: メニューを確認し、事前に選んで注文  
- 受け取り: 店舗で受け取り、待ち時間を短縮  

例えば、仕事や学校帰りに「夕食を買おう」と思ったとき、事前に予約しておけば、売り切れの心配なくスムーズに受け取れます。

## 開発の背景
学生時代、食べたい食事が売り切れていた経験から、  
「食べたい食事を確実に予約購入できる仕組みが必要だ」と考えました。  

この課題を検証するため、200人のユーザーインタビューや飲食店への営業を実施。  
その結果、事前登録ユーザー15人と飲食店1店舗の事前登録賛同を得、アプリ開発の必要性を感じ他のをきっかけに、エンジニアリングを学びながら開発に取り組みました。



・本デモアプリの操作方法

<img src="https://raw.githubusercontent.com/KKoske/MeeTEaT/main/MeeTEaT3MB.gif" width="300"> 


1:赤い囲みをタップして進む。

2:「ドミノピザ」をクリック！

3:「ハロウィンボルケーノ&ハロウィンブラックチキン」をクリック！

4:注文確認画面、注文受け取り画面は未実装！



<br><br><br>


# 技術README

## 主な機能:(実装済み機能のみここに記載)
- 飲食店の一覧表示
- 飲食店の商品一覧の表示
- ユーザーフレンドリーなUI/UX設計
- 現在地と設定地点のルートを表示するMAP機能
## 使用技術
- 言語: Kotlin  
- UI: XML（今後Jetpack Composeへ移行予定）  
- データベース: SQLite（今後Firestore/PostgreSQLへ移行予定）  
- API: Google Maps API, Directions API  
- バージョン管理: GitHub
- 推奨設定:APIレベル33(Android 13.0"Tiramisu"| arm64)



# UI/UXの工夫と効果
テイクアウトアプリの利用に際して、ユーザーの心理仮説を最大化させるUI/UX設計。

### 1.　横3店舗表示:1画面でスキャンできる情報量を増加 

　　　←左が3画像(今)　　右が2.5画像の場合→

<p>
  <img src="https://raw.githubusercontent.com/KKoske/MeeTEaT/8302db2992061427ed0b5de57e7f2b58d88787d5/3.gif" width="200">
  <img src="https://raw.githubusercontent.com/KKoske/MeeTEaT/7d9ed4318bed95ebfa48c2149bc50ef0deb81667/2.gif" width="200">
</p>
<dev>
 
### 2.グリッドの画像はみ出し設計: 重要な情報を目立たせるデザイン  

　　　←左：右端のはみ出しが、上から下へ順に大きくなる（今）右：全て同じの場合→

   <p>
  <img src="https://raw.githubusercontent.com/KKoske/MeeTEaT/7d9ed4318bed95ebfa48c2149bc50ef0deb81667/Before.gif" width="200">
  <img src="https://raw.githubusercontent.com/KKoske/MeeTEaT/7d9ed4318bed95ebfa48c2149bc50ef0deb81667/after.gif" width="200">
</p>
<dev>
 
### 3.ルート上の店舗は3x3グリッドで表示: 決定スピードを向上させる機能配置

　　　←左：3x3グリッド（今）　　右：3x1→

   <p>
  <img src="https://raw.githubusercontent.com/KKoske/MeeTEaT/7d9ed4318bed95ebfa48c2149bc50ef0deb81667/3x3.gif" width="200">
  <img src="https://raw.githubusercontent.com/KKoske/MeeTEaT/7d9ed4318bed95ebfa48c2149bc50ef0deb81667/1x3.gif" width="200">
</p>
<dev>

## 今後の展望
MeeTEaTをよりモダンで拡張性の高いアプリへ進化させるため、以下の改善を予定しています。

- 品質向上: テストコードの導入
- 保守性の向上: クラス・メソッドの責任分離によるコードの整理  
- 設計のモダン化: MVVMアーキテクチャの導入による、UIとデータの責務分離  
- パフォーマンス最適化: 非同期処理の最適化による、スムーズなAPI動作
- UIのモダン化: Jetpack Composeへの移行による、柔軟なUI設計  
- データ管理の拡張: Firebase/PostgreSQLへの移行による、リアルタイム反応やデータ運用など用途に適したデータ設計

 


## デモ版操作の注意点 

画像遷移のポイントとなる部分には色をつけておりますが、店舗・商品表示画面でタップできるポイントは構造上まだ色を付けれていないため、ここにて記載します。

1. 店舗一覧画面では[ルート上の店舗]カードのドミノピザの画像をタップすることで 遷移します。[ルート上の店舗]カード以外の画像をタップしてもドミノピザ以外のダミーデータが入っていない為、何も表示されないページに遷移されます。

2. グリッドのはみ出しは、グリッドの画像をスワイプすると、はみ出しが表示されます。

3. ドミノピザ商品ページに辿り着いたら、ハロウィンボルケーノ&ハロウィンブラックチキンを選択してください、他の画像はタップしても遷移はしないですので注意して下さい。

4. 実装・調整中ですので、適切な表示がされていない箇所などがある可能性があります。

<br><br>

補足: 商品画像にハロウィン要素があるのは、開発時期がハロウィンシーズンだったためです。イベントには参加できませんでしたが、開発の中で雰囲気を楽しみたくて取り入れました。🎃 



