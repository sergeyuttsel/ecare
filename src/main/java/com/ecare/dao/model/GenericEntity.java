package com.ecare.dao.model;

import javax.persistence.*;

@MappedSuperclass
public abstract class GenericEntity {

        @Id
        @Column(name = "id", nullable = false)
        @GeneratedValue(strategy=GenerationType.IDENTITY)
        private int id;

        public GenericEntity() {}
        
        public GenericEntity(int id) {
                this.id = id;
        }
        
        public int getId() {
                return id;
        }
        public void setId(int id) {
                this.id = id;
        }

}
