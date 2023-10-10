package org.macnss.DAO;

import org.macnss.Database.Database;
import org.macnss.Entities.ADocument;

import java.sql.Connection;

public interface IDocumentDAO {
    final Connection connection = Database.getConnection();
    final String TABLE = "documents";

    final String ID = "id";

    final String TITLE = "title";

    final String TYPE = "type";
    final String CREATED_AT = "createdAt";

    final String PRICE = "price";

    final String REFUND_RATE = "refund_rate";

    final String FOLDER_ID = "folder_id";
    public ADocument insert(ADocument ADocument);

    public float calculateRefundAmount(ADocument ADocument);


}
