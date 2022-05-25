<?php
if ($_SERVER["REQUEST_METHOD"] == 'POST') {
    $error_code = 500;
    $fields = ["name", "visit_purpose", "drop_off", "pick_up", "comments"];

    try {
        // get ENV variables and set up database connection
        $config = json_decode(file_get_contents(".env.json"), true);

        $db = new PDO(
            "mysql:host=$config[host];port=$config[port];dbname=$config[dbname]",
            $config["username"],
            $config["password"]
        );

        $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        $db->exec("SET NAMES 'utf8'");

        // validate that all required data was submitted
        $form_data = json_decode(file_get_contents("php://input"), true);

        foreach ($fields as $field) {
            if ($key == "comments") continue;
            if (!$form_data[$field]) {
                $error_code = 400;
                throw new Exception("Missing required fields!");
            }
        }

        // Prepare and execute database query
        $sql = $db->prepare("INSERT INTO ClientSignIn VALUES(:name, :visit_purpose, :drop_off, :pick_up, :comments, NOW(), :office);");

        foreach ($fields as $field) {
            $param_type = is_bool($form_data[$field]) ? PDO::PARAM_BOOL : PDO::PARAM_STR;
            $sql->bindParam(":".$field, $param_type);
        }

        $sql->execute();
        echo "Signed in successfully!";

    } catch (Exception $e) {
        error_log($e->getMessage());
        http_response_code($error_code);

        echo $error_code != 500 ?
            $e->getMessage() :
            "Server Error: Failed to submit data. Please try again!";
    }
} else echo "OK" . ($_SERVER["SERVER_NAME"] == "signin.gm-cpas.test" ? " TEST" :  "");
