package umc.th.juinjang.service.ScrapService;

import umc.th.juinjang.model.entity.enums.ScrapActionType;

public interface ScrapService {

  void createScrap(long limjangId);

  void deleteScrap(long limjangId);
}
