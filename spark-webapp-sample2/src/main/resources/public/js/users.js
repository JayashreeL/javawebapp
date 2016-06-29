var getUsersData = function() {
  var xhr = new XMLHttpRequest();

  //JSONデータ用のURLを指定
  var url = '/users/1';

  //コネクションを開く
  xhr.open('GET', url, true);
  
  //リクエストを送信
  xhr.send();

  //レスポンスを受け取った後に発生するイベントハンドラを上書き
  xhr.onload = function() {
    if (xhr.status >= 200 && xhr.status < 400) {
      // レスポンスのステータスにエラーがない想定

      // 受け取ったレスポンスデータをJSON形式でパースする
      var data = JSON.parse(xhr.responseText);
      console.log(data);

      // 画面表示するデータをHTMLに整形
      var out = '';
      out += '<table>';
      out += '<tr><th>ユーザーID</th><th>ユーザー名</th><th>メールアドレス</th></tr>';
      if (data.length) { //データが複数件入っている場合
        for(var i = 0; i < data.length; i++) {
          // 各データは、JSONをパースした結果プロパティと同様に扱うことができます。
          out += '<tr>';
          out += '<td>' + data[i].id + '</td>';
          out += '<td>' + data[i].name + '</td>';
          out += '<td>' + data[i].email + '</td>';
          out += '</tr>';
        }
      } else {
          out += '<tr>';
          out += '<td>' + data.id + '</td>';
          out += '<td>' + data.name + '</td>';
          out += '<td>' + data.email + '</td>';
          out += '</tr>';
      }
      out += '</table>';

      //画面のデータ表示に用意した部分に書き込み
      document.getElementById('users-data').innerHTML = out;
    }
  };
};

// ページが表示された時に指定された関数を実行する関数
function ready(fnc) {
  if (document.readyState !== 'loading'){
    fnc();
  } else {
    document.addEventListener('DOMContentLoaded', fnc);
  }
}
// 初期化
function init() {
  console.log('init');
  getUsersData();
}
// 初期化設定
ready(init);
