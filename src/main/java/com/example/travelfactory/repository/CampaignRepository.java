package com.example.travelfactory.repository;

import com.example.travelfactory.Entity.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign,Long> {


}
