package org.macnss.Services;

import org.macnss.DAO.impl.DocumentDAO;
import org.macnss.Entities.ADocument;

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
