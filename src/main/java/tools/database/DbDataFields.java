package tools.database;

public class DbDataFields {

    public enum MySqlDataFields {
        INT11("int(11)"),
        INT22("int(22)"),
        BIGINT22("bigint(22)"),
        VARCHAR128("varchar(128)"),
        VARCHAR256("varchar(256)"),
        DATETIME("datetime"),
        DATE("date"),
        TEXT("text");

        private String string = new String();

        MySqlDataFields(String string) {
            this.string = string;
        }

        public String getValue() {
            return string;
        }
    }
}

