package org.macnss.Services;

import org.macnss.dao.impl.DocumentDAO;
import org.macnss.entity.ADocument;

import java.sql.SQLException;
import java.util.List;

public class DocumentService {

    private final DocumentDAO Dao = new DocumentDAO();



    public ADocument create(ADocument ADocument){
        if (Dao.insert(ADocument) != null){
            return ADocument;
        }else {
            return null;
        }
    }



}
