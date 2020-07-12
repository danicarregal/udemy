package com.carregal.SpringSoap.beans.mappers;

import org.mapstruct.Mapper;

import com.carregal.SpringSoap.beans.Status;
import com.carregal.SpringSoap.jaxb.OpStatus;

@Mapper(componentModel = "spring")
public interface StatusMapper {

	public OpStatus statusToOpStatus(Status status);
}
