package school21.schoolbe2.mapper;

import school21.schoolbe2.dto.*;
import school21.schoolbe2.persistence.entity.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Mapper {

    public AddressEntity AddrDtoToEntity(AddressDto addressDto) {
        if (addressDto == null) {
            throw new IllegalArgumentException("Object is null");
        }
        AddressEntity address = new AddressEntity(addressDto.getCountry(), addressDto.getCity(), addressDto.getStreet());
        return address;
    }

    public AddressDto AddrEntityToDto(AddressEntity address) {
        if (address == null) {
            throw new IllegalArgumentException("Object is null");
        }
        return new AddressDto(address.getAddressId(), address.getCountry(), address.getCity(), address.getStreet());
    }


    public ClientEntity ClientDtoToEntity(ClientDto clientDto) {
        if (clientDto == null) {
            throw new IllegalArgumentException("Object is null");
        }
        return new ClientEntity(clientDto.getClientName(), clientDto.getClientSurname(), clientDto.getBirthday(),
                clientDto.getGender(), clientDto.getRegistrationDate(), AddrDtoToEntity(clientDto.getAddress()));
    }

    public ClientDto ClientEntityToDto(ClientEntity clientEntity) {
        if (clientEntity == null) {
            throw new IllegalArgumentException("Object is null");
        }
        return new ClientDto(clientEntity.getId(), clientEntity.getClientName(), clientEntity.getClientSurname(), clientEntity.getBirthday(), clientEntity.getGender(),
                clientEntity.getRegistrqtionDate(), AddrEntityToDto(clientEntity.getAddress()));
    }

    public ImageEntity ImageDtoToEntity(ImageDto imageDto) {
        if (imageDto == null) {
            throw new IllegalArgumentException("Object is null");
        }
        return new ImageEntity(imageDto.getImages());
    }

    public ImageDto ImageEntityToDto(ImageEntity imageEntity) {
        if (imageEntity == null) {
            throw new IllegalArgumentException("Object is null");
        }
        return new ImageDto(imageEntity.getImageId(), imageEntity.getImages());
    }

    public ProductEntity ProDtoToEntity(ProductDto productDto, SupplierEntity supplier, ImageEntity imageEntity) {
        if (productDto == null) {
            throw new IllegalArgumentException("Object is null");
        }
        return new ProductEntity(productDto.getProductName(), productDto.getCategory(), productDto.getPrice(), productDto.getAvailableStock(), productDto.getLastUpdateDate(), supplier, imageEntity);
    }

    public ProductDto ProEntityToDto(ProductEntity productEntity) {
        if (productEntity == null) {
            throw new IllegalArgumentException("Object is null");
        }
        UUID imageId = null;
        if (productEntity.getImageEntity() != null) {
            imageId = productEntity.getImageEntity().getImageId();
        }
        return new ProductDto(productEntity.getProductId(), productEntity.getProductName(), productEntity.getCategory(), productEntity.getPrice(),
                productEntity.getAvailableStock(), productEntity.getLastUpdateDate(), productEntity.getSupplier().getSupplierId(), imageId);
    }

    public SupplierEntity SuppDtoToEntity(SupplierDto supplierDto) {
        if (supplierDto == null) {
            throw new IllegalArgumentException("Object is null");
        }
        return new SupplierEntity(supplierDto.getName(), AddrDtoToEntity(supplierDto.getAddress()), supplierDto.getPhoneNumber());
    }

    public SupplierDto SuppEntityToDto(SupplierEntity supplier) {
        if (supplier == null) {
            throw new IllegalArgumentException("Error.Object is null");
        }
        return new SupplierDto(supplier.getSupplierId(), supplier.getName(), AddrEntityToDto(supplier.getAddress()), supplier.getPhoneNumber());
    }
}
