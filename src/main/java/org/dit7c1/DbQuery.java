package org.dit7c1;

/*
Класс генерирует sql запросы.
Я предпочёл использовать констаны, возможно из-за этого код раздутый, но мне показалось, что так читабельней, могу ошибаться.
 */

public class DbQuery {
    private final String SELECT = "SELECT";
    private final String FROM = "FROM";
    private final String WHERE = "WHERE";
    private final String ORDER_BY = "ORDER BY";
    private final String INNER_JOIN = "INNER JOIN";
    private final String TOP = "TOP";
    private final String IS_NOT_NULL = "IS NOT NULL";
    private final String AND = "AND";
    private final String IN = "IN";
    private final String ON = "ON";
    private final String SPACE = " ";
    private final String ASTERISK = "*";
    private final String COMMA = ",";
    private final String DOT = ".";
    private final String EQUALS = "=";
    private final String SQUARE_BRACKET_OPEN = "[";
    private final String SQUARE_BRACKET_CLOSE = "]";
    private final String ROUND_BRACKET_OPEN = "(";
    private final String ROUND_BRACKET_CLOSE = ")";


    private StringBuilder stringResultQuery;
    private StringBuilder sbFrom;
    private StringBuilder sbField;
    private StringBuilder sbOrderBy;
    private StringBuilder sbWhereNotNull;
    private StringBuilder sbWhereIn;
    private StringBuilder sbJoin;
    private StringBuilder sbLimit;

    public DbQuery query(String nameTable) {
        sbFrom = new StringBuilder();

        sbFrom.append(FROM)
              .append(SPACE)
              .append(SQUARE_BRACKET_OPEN)
              .append(nameTable)
              .append(SQUARE_BRACKET_CLOSE);

        stringResultQuery = new StringBuilder().append(SELECT)
                                               .append(SPACE)
                                               .append(ASTERISK)
                                               .append(SPACE)
                                               .append(sbFrom);
        show(stringResultQuery.toString());

        return this;
    }

    public DbQuery select(String... fields) {
        sbField = new StringBuilder();

        if (fields.length > 1) {
            for (int index = 0; index < fields.length; index++) {
                sbField.append(SQUARE_BRACKET_OPEN)
                       .append(fields[index]);

                if (index == fields.length - 1) {
                    sbField.append(SQUARE_BRACKET_CLOSE);
                } else {
                    sbField.append(SQUARE_BRACKET_CLOSE)
                           .append(COMMA)
                           .append(SPACE);
                }
            }
        } else {
            sbField.append(SQUARE_BRACKET_OPEN)
                   .append(fields[0])
                   .append(SQUARE_BRACKET_CLOSE);
        }

        stringResultQuery = new StringBuilder().append(SELECT)
                                               .append(SPACE)
                                               .append(sbField)
                                               .append(SPACE)
                                               .append(sbFrom);
        show(stringResultQuery.toString());

        return this;
    }

    public DbQuery orderBy(String fieldSort) {
        sbOrderBy = new StringBuilder();

        sbOrderBy.append(ORDER_BY)
                 .append(SPACE)
                 .append(SQUARE_BRACKET_OPEN)
                 .append(fieldSort)
                 .append(SQUARE_BRACKET_CLOSE);

        stringResultQuery = new StringBuilder().append(SELECT)
                                               .append(SPACE)
                                               .append(sbField)
                                               .append(SPACE)
                                               .append(sbFrom)
                                               .append(SPACE)
                                               .append(sbOrderBy);
        show(stringResultQuery.toString());

        return this;
    }

    public DbQuery whereNotNull(String strWhere) {
        sbWhereNotNull = new StringBuilder();

        sbWhereNotNull.append(WHERE)
                      .append(SPACE)
                      .append(SQUARE_BRACKET_OPEN)
                      .append(strWhere)
                      .append(SQUARE_BRACKET_CLOSE)
                      .append(SPACE)
                      .append(IS_NOT_NULL);

        stringResultQuery = new StringBuilder().append(SELECT)
                                               .append(SPACE)
                                               .append(sbField)
                                               .append(SPACE)
                                               .append(sbFrom)
                                               .append(SPACE)
                                               .append(sbWhereNotNull)
                                               .append(SPACE)
                                               .append(sbOrderBy);
        show(stringResultQuery.toString());

        return this;
    }

    public DbQuery whereIn(String obj, String[] lang) {
        sbWhereIn = new StringBuilder();

        if (lang.length > 1) {
            sbWhereIn.append(AND)
                     .append(SPACE)
                     .append(SQUARE_BRACKET_OPEN)
                     .append(obj)
                     .append(SQUARE_BRACKET_CLOSE)
                     .append(SPACE)
                     .append(IN)
                     .append(SPACE);
            for (int index = 0; index < lang.length; index++) {
                sbWhereIn.append(ROUND_BRACKET_OPEN)
                         .append("'")
                         .append(lang[index])
                         .append("'");
                if (index == lang.length - 1) {
                    sbWhereIn.append(ROUND_BRACKET_CLOSE);
                } else {
                    sbWhereIn.append(ROUND_BRACKET_CLOSE)
                             .append(COMMA)
                             .append(SPACE);
                }
            }
        } else {
            sbWhereIn.append(SPACE)
                     .append(AND)
                     .append(SPACE)
                     .append(SQUARE_BRACKET_OPEN)
                     .append(obj)
                     .append(SQUARE_BRACKET_CLOSE)
                     .append(SPACE)
                     .append(IN)
                     .append(SPACE)
                     .append(ROUND_BRACKET_OPEN)
                     .append("'")
                     .append(lang[0])
                     .append("'")
                     .append(ROUND_BRACKET_CLOSE);
        }

        stringResultQuery = new StringBuilder().append(SELECT)
                                               .append(SPACE)
                                               .append(sbField)
                                               .append(SPACE)
                                               .append(sbFrom)
                                               .append(SPACE)
                                               .append(sbWhereNotNull)
                                               .append(SPACE)
                                               .append(sbWhereIn)
                                               .append(SPACE)
                                               .append(sbOrderBy);
        show(stringResultQuery.toString());

        return this;
    }

    public DbQuery join(String table, String key1, String key2) {
        sbJoin = new StringBuilder();

        String[] separator1 = key1.split("\\.");
        String[] separator2 = key2.split("\\.");
        sbJoin.append(INNER_JOIN)
              .append(SPACE)
              .append(SQUARE_BRACKET_OPEN)
              .append(table)
              .append(SQUARE_BRACKET_CLOSE)
              .append(SPACE)
              .append(ON)
              .append(SPACE)
              .append(SQUARE_BRACKET_OPEN)
              .append(separator1[0])
              .append(SQUARE_BRACKET_CLOSE)
              .append(DOT)
              .append(SQUARE_BRACKET_OPEN)
              .append(separator1[1])
              .append(SQUARE_BRACKET_CLOSE)
              .append(SPACE)
              .append(EQUALS)
              .append(SPACE)
              .append(SQUARE_BRACKET_OPEN)
              .append(separator2[0])
              .append(SQUARE_BRACKET_CLOSE)
              .append(DOT)
              .append(SQUARE_BRACKET_OPEN)
              .append(separator2[1])
              .append(SQUARE_BRACKET_CLOSE);

        stringResultQuery = new StringBuilder().append(SELECT)
                                               .append(SPACE)
                                               .append(sbField)
                                               .append(SPACE)
                                               .append(sbFrom)
                                               .append(System.lineSeparator())
                                               .append(sbJoin)
                                               .append(SPACE)
                                               .append(sbWhereNotNull)
                                               .append(SPACE)
                                               .append(sbWhereIn)
                                               .append(SPACE)
                                               .append(sbOrderBy);
        show(stringResultQuery.toString());

        return this;
    }

    public DbQuery limit(int i) {
        sbLimit = new StringBuilder();

        sbLimit.append(TOP)
               .append(SPACE)
               .append(ROUND_BRACKET_OPEN)
               .append(i)
               .append(ROUND_BRACKET_CLOSE);

        stringResultQuery = new StringBuilder().append(SELECT)
                                               .append(SPACE)
                                               .append(sbLimit)
                                               .append(SPACE)
                                               .append(sbField)
                                               .append(SPACE)
                                               .append(sbFrom)
                                               .append(System.lineSeparator())
                                               .append(sbJoin)
                                               .append(SPACE)
                                               .append(sbWhereNotNull)
                                               .append(SPACE)
                                               .append(sbWhereIn)
                                               .append(SPACE)
                                               .append(sbOrderBy);
        show(stringResultQuery.toString());

        return this;
    }

    private void show(String showStringResult) {
        System.out.println(showStringResult);
        System.out.println();
    }

    // Для примера: "Внутренний класс" - диалект PostgreSQL (SELECT fields) - SELECT Id, Name, ... .
    class DialectPostgreSQL extends DbQuery {
        @Override
        public DbQuery select(String... fields) {
            sbField = new StringBuilder();

            if (fields.length > 1) {
                for (int index = 0; index < fields.length; index++) {
                    sbField.append(fields[index]);
                    if (index != fields.length - 1) {
                        sbField.append(COMMA)
                               .append(SPACE);
                    }
                }
            } else {
                sbField.append(fields[0]);
            }

            stringResultQuery = new StringBuilder().append(SELECT)
                                                    .append(SPACE)
                                                    .append(sbField)
                                                    .append(SPACE)
                                                    .append(sbFrom);
            show(stringResultQuery.toString());

            return this;
        }
    }
}


class TestMain {
    public static void main(String[] args) {
        DbQuery db = new DbQuery();
        DbQuery.DialectPostgreSQL dialectPostgreSQL = db.new DialectPostgreSQL();

        System.out.println("-----------------------------------");
        System.out.println("|-=Test query from GIF animation=-|");
        System.out.println("-----------------------------------");
        db.query("Books")
          .select("Id", "Name")
          .orderBy("PublishDate")
          .whereNotNull("AuthorId")
          .whereIn("Lang", new String[]{"en", "fr"})
          .join("Authors", "Authors.Id", "Books.AuthorId")
          .limit(5);

        System.out.println();
        System.out.println("--------------------------------------");
        System.out.println("|-=Test dialect PostgreSQL (SELECT fields)=-|");
        System.out.println("--------------------------------------");
        db.query("Books");
        dialectPostgreSQL.select("Id", "Name"); // переопределенный класс с другим диалектом
        db.orderBy("PublishDate")
          .whereNotNull("AuthorId")
          .whereIn("Lang", new String[]{"en", "fr"})
          .join("Authors", "Authors.Id", "Books.AuthorId")
          .limit(5);

    }
}