package me.nagatani.dev.spark.webapp.sample2;

import com.google.gson.Gson;
import me.nagatani.dev.spark.webapp.sample2.models.User;
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
            
            // データベースから全件取得して返す
            return Users.selectAll();
            
        }, gson::toJson);
        // ↑Gson.toJsonメソッドをポインタで渡す
        
        // http://localhost:4567/users/?
        get("/users/:id", (req, res) -> {
            
            int id = Integer.parseInt(req.params(":id"));
            User u = Users.selectByID(id);
            if (u == null) {
                halt(404, "Not Found!!!");
                return null;
            }
            return u;
        }, gson::toJson);
        
        // NetBeans上で実行する際にサーバーの停止に必要
        // http://localhost:4567/stop
        get("/stop", (req, res) -> {
            stop();
            return null;
        });
    }
}
