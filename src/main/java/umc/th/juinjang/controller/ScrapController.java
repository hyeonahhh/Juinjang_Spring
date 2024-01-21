package umc.th.juinjang.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.th.juinjang.apiPayload.ApiResponse;
import umc.th.juinjang.apiPayload.code.status.SuccessStatus;
import umc.th.juinjang.converter.limjang.LimjangPostConverter;
import umc.th.juinjang.model.dto.limjang.LimjangPostRequestDTO;
import umc.th.juinjang.model.dto.limjang.LimjangPostResponseDTO;
import umc.th.juinjang.model.dto.limjang.LimjangPostResponseDTO.PostDTO;
import umc.th.juinjang.model.entity.enums.ScrapActionType;
import umc.th.juinjang.repository.limjang.ScrapRepository;
import umc.th.juinjang.service.LimjangService.LimjangCommandService;
import umc.th.juinjang.service.ScrapService.ScrapCommandService;

@RestController
@RequestMapping("/api/scrap")
@RequiredArgsConstructor
@Validated
public class ScrapController {

  private final ScrapCommandService scrapCommandService;

  @CrossOrigin
  @Operation(summary = "스크랩 등록/삭제 API")
  @PostMapping("/{limjangId}")
  public ApiResponse<PostDTO> postLimjang(
      @PathVariable(name = "limjangId") @Valid Long limjangId){

    ScrapActionType scrapActionType = scrapCommandService.actionScrap(limjangId);
    if(scrapActionType == ScrapActionType.SCRAP){ // 스크랩 동작이 이루어짐
      return ApiResponse.of(SuccessStatus._SCRAP_ACTION_SCRAP, null);
    }
    return ApiResponse.of(SuccessStatus._SCRAP_ACTION_UNSCRAP, null);
  }
}