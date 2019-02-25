package com.gdm.vehicleapi.hook;

import com.gbm.vehicle.pojos.Hook;
import com.gbm.vehicle.pojos.Position;
import com.gbm.vehicle.pojos.Vehicle;
import com.gdm.vehicleapi.GenericController;
import com.gdm.vehicleapi.util.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
public class HookController extends GenericController<Hook, HookService> {

  @Autowired
  public HookController(@Value("${app.max.rows}") int maxRows, HookService subscribeService) {
    super(maxRows, subscribeService);
  }

  @PostMapping("/vehicle/{uuid}/hook")
  public Hook save(@PathVariable(name = "uuid") final String vehicleUuidStr,
                       @Valid @RequestBody Hook hook) {
    return s.save(hook, UUID.fromString(vehicleUuidStr));
  }

  @DeleteMapping("/hook/{uuid}")
  public void delete(@PathVariable(name = "uuid") final String hookUuidStr) {
    super.deleteById(hookUuidStr);
  }

  @GetMapping("/hook/{uuid}")
  public Hook findById(@PathVariable(name = "uuid") final String hookUuidStr) {
    return super.findById(hookUuidStr);
  }

  @GetMapping("/vehicle/{uuid}/hooks")
  public ResponseEntity<List<Hook>> findAll(@PathVariable(name = "uuid") final String hookUuidStr,
                                            @RequestParam(name = "page", defaultValue = "0") final  Integer page) {
    PageRequest pageRequest = PageRequest.of(page, maxRows);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(pageRequest);
    Page<Hook> pPage = s.findAllPositionsByVehicleId(UUID.fromString(hookUuidStr), pageRequest);
    return new ResponseEntity<>(pPage.getContent(), headers, HttpStatus.OK);
  }


}
