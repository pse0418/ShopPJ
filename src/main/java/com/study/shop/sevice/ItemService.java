package com.study.shop.sevice;

import com.study.shop.domain.item.Item;
import com.study.shop.domain.item.ItemRepository;
import com.study.shop.web.dto.ItemDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// DTO -> Entity (Entity Class)
// Entity -> DTO (DTO Class)

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public void save(ItemDTO itemDTO) {
        Item item = Item.toItem(itemDTO);
        itemRepository.save(item);
    }

    public ItemDTO search(ItemDTO itemDTO) {
        Optional<Item> byItemNm = itemRepository.findByItemNm(itemDTO.getItemNm());
        if (byItemNm.isPresent()) {
            Item item = byItemNm.get();
            ItemDTO dto = ItemDTO.toItemDTO(item);
            return dto;
        } else {
            return null;
        }
    }

    public List<ItemDTO> findAll() {
        List<Item> itemList = itemRepository.findAll();
        List<ItemDTO> itemDTOList = new ArrayList<>();
        for (Item item: itemList) {
            itemDTOList.add(ItemDTO.toItemDTO(item));
        }
        return itemDTOList;
    }

    public void deleteById(Long id) {
        itemRepository.deleteById(id);
    }

    public ItemDTO findById(Long id) {
        Optional<Item> optionalItem = itemRepository.findById(id);  // 옵셔널 객체를
        if (optionalItem.isPresent()) {
        /*  Item item = optionalItem.get();                         // get으로 받아야 entity객체가 보이고
            ItemDTO itemDTO = ItemDTO.toItemDTO(item);              // entity객체를 dto로 변환해서
            return  itemDTO;                                        // 컨트롤러에 보내준다   */
            return ItemDTO.toItemDTO(optionalItem.get());       // 윗 세줄을 한줄로 표현
        } else {
            return null;
        }
    }

    @Transactional
    public void updateViewCount(Long id) {
        itemRepository.updateViewCount(id);
    }

    public Page<ItemDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1;    // page는 0부터, 사용자가 보는 첫페이지는 1이기 때문에
        int pageLimit = 2;  //한 페이지에 보여줄 글 갯수
        // 한페이지당 2개를 보여주고 조회수롤 내림차순 정렬
        Page<Item> items =
        itemRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "viewCount")));

        Page<ItemDTO> itemDTOS = items.map(item -> new ItemDTO(item.getId(), item.getItemNm(), item.getPrice(), item.getViewCount()));
        return itemDTOS;
    }
}
