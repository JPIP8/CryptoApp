package com.example.idrovo_final;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArticleCrypto {

        @SerializedName("id")
        @Expose
        private String id;

        @SerializedName("symbol")
        @Expose
        private String symbol;

        @SerializedName("name")
        @Expose
        private String name;

        @SerializedName("current_price")
        @Expose
        private String current_price;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCurrent_price() {
            return current_price;
        }

        public void setCurrent_price(String current_price) {
            this.current_price = current_price;
        }


    }

