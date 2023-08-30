package api;

import lombok.Data;
import models.IsbnModel;

import java.util.List;

@Data
public class AddBooksListModel {
    String userId;
    List<IsbnModel> collectionOfIsbns;
}
