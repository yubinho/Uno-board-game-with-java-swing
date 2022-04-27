# Intro

這是一款以java為基礎開發的Uno桌遊，可供2~4名玩家進行，目前尚未包含AI


## 各檔案性質

| java檔  | 功能 |
| ------------- |:-------------:|
|  Game    | 主要負責整體遊戲流程進行|
| UnoDeck      | 主要負責維護目前牌組|
| UnoCard      | 定義每張牌的顏色與數字|
| Player     | 紀錄玩家資料與手牌現況     |
| UnoHome      | 負責首頁的畫面與事件     |
| UnoGuide      | 負責遊戲說明畫面     |
| AddPlayerName      | 負責輸入遊戲玩家名稱的畫面|
| GameStage      | 負責主要遊戲的畫面     |
| PopUp      | 負責選取某張牌的彈出式畫面     |
| ChooseWildCardColor|負責選取萬能牌顏色的畫面|
| WinnerFrame| 負責終場贏家的彈出式畫面|

| 資料夾  | 功能 |
| ------------- |:-------------:|
|  project    | 專題程式檔|
|  project/picture    | 每張卡牌與其它按鈕的圖像檔|
|  image    | readme的圖像檔|

# Requirements
Java SE Development Kit

[JRE download here](https://www.oracle.com/java/technologies/downloads/)

# Installation

在安裝好Java後，點擊Uno.exe進行遊戲

# Game rules

1. 獲勝規則
   
   先將自己手上的牌全部打出的人取得勝利

2. 出牌規則
   
   必須和上個玩家所出牌的顏色、數字或者圖案相同，當玩家無牌可出或不願意出牌則從牌堆抽一張牌，並輪到下一位玩家

3. 牌組規則
   1. 普通牌 
      
      由紅、綠、黃、藍組成，每種顏色有19張牌，1~9數字牌各2張，0數字牌僅有一張
   2. 功能牌

      由紅、綠、黃、藍組成，分別為跳過、翻轉、+2
      
      | 功能牌  | 使用前提 |效果 |
      | ------------- |:-------------:|:-------------:|
      |  跳過    | 上一張顏色相同或同是跳過|下一名玩家停止出牌|
      |  翻轉      | 上一張顏色相同或同是翻轉|出牌順序反轉|
      |  +2     | 上一張顏色相同或同是+2|下一名玩家需抽2張牌|
   3. 萬能牌

      又稱為黑牌、王牌，分為變色牌、+4

      | 功能牌  | 使用前提 |效果 |
      | ------------- |:-------------:|:-------------:|
      |  變色牌    | 無|隨意指定下一名玩家出牌顏色|
      |  +4      | 無|隨意指定下一名玩家出牌顏色，並且需要再抽4張牌|

# Usage

* 首頁，點選開始可以進行新一輪遊戲，也可點選規則說明或離開
  
  ![This is a alt text.](./image/home.png)

* 規則說明，可逐頁查看說明，查看完畢可點選離開返回首頁

  ![This is a alt text.](./image/guide.png)
* 輸入玩家名稱，此遊戲必須2~4人遊玩，輸入完畢即可開始

  ![This is a alt text.](./image/addName.png)
* 主要遊戲頁面，可點選下方卡牌進行出牌，或者抽一張牌結束回合，也可在即將贏得遊戲時點選Uno(需<=兩張牌)，未點選會導致無法結束並且被懲罰抽2張牌

  ![This is a alt text.](https://github.com/yubinho/Uno-board-game-with-java-swing/blob/main/image/Main.PNG)
* 出牌確認，點選使用按鈕後，系統會判斷出牌是否滿足規則，滿足則輪到下一位玩家，反之則重新出牌

  ![This is a alt text.](./image/submit.png)
* 萬能牌選色，如果出的是萬能牌，需要選擇下一回合卡牌顏色

  ![This is a alt text.](./image/choose.png)

* 結束頁面，可點選退出重新開始或者直接退出並結束遊戲

  ![This is a alt text.](./image/win.png)



