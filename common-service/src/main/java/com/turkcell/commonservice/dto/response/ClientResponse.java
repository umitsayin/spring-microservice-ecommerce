package com.turkcell.commonservice.dto.response;

public record ClientResponse <T>(boolean status, T data) {
}
