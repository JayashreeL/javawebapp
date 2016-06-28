package me.nagatani.dev.spark.webapp.sample2;

import com.google.gson.Gson;
import me.nagatani.dev.spark.webapp.sample2.models.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static spark.Spark.*;

/**
 *
 * @author Nagatani
 */
public class App {

    public static void main(String[] args) {
        
        // Gsonを使うための準備
        Gson gson = new Gson();
        
        // ログ出力機能の初期化
        Logger logger = LoggerFactory.getLogger(App.class);
        
        // /src/main/resources/public を公開ディレクトリとする
        staticFileLocation("/public");

        // http://localhost:4567/users
        get("/users", (req, res) -> {
            
            /*
            // Userのリストを作成して返す
            List<User> users = new ArrayList<>();
            users.add(new User(1, "name1", "name1@sample.com"));
            users.add(new User(1, "name2", "name2@sample.com"));
            users.add(new User(1, "name3", "name3@sample.com"));
            */
            
            
            // データベースから全件取得して返す
            return Users.selectAll();
            
        }, gson::toJson);
        // ↑Gson.toJsonメソッドをポインタで渡す
        
        // NetBeans上で実行する際にサーバーの停止に必要
        // http://localhost:4567/stop
        get("/stop", (req, res) -> {
            stop();
            return null;
        });
    }
}
