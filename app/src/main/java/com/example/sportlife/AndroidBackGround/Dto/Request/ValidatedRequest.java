package com.example.sportlife.AndroidBackGround.Dto.Request;

import androidx.versionedparcelable.NonParcelField;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidatedRequest {
    private String tokenRefresh;
}
