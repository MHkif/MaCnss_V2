package org.macnss.dao.impl;

import org.macnss.dao.IDocumentDAO;
import org.macnss.entity.ADocument;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class DocumentDAO implements IDocumentDAO {



    @Override
    public ADocument insert(ADocument ADocument) {
        String sql = "INSERT INTO "+TABLE+"(id, title, type, price, refund_rate, folder_id, createdAt)  VALUES(?,?,?,?,?,?,?) ";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            preparedStatement.setString(1, ADocument.getId());
            preparedStatement.setString(2, ADocument.getTitle());
            preparedStatement.setString(3, String.valueOf(ADocument.getType()));
            preparedStatement.setFloat(4, ADocument.getPrice());
            preparedStatement.setFloat(5, ADocument.getRefund_rate());
            preparedStatement.setString(6, ADocument.getFolder().getId());
            preparedStatement.setDate(7, (Date) ADocument.getCreatedAt());

            if(preparedStatement.executeUpdate() > 0){
                return ADocument;
            }else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public float calculateRefundAmount(ADocument ADocument){

            float totale = 0;


            return  totale;

    }


}
