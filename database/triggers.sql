
DROP TRIGGER IF EXISTS after_insert_user_tariffs;
DELIMITER //
Create trigger after_insert_user_tariffs
After update on `tariffsdb`.`user_tariffs`
For each row
Begin
	Declare messages_counts int ;
    Declare calls_time Time ;
    Declare internet  int;
    
    Select total_messages_counts, total_calls_time, total_internet into messages_counts, calls_time, internet
    From user_tariffs 
    Join contract_tariffs on contract_tariffs.id=user_tariffs.contract_tariffs_id
    Join prepaid_tariffs on prepaid_tariffs.id=user_tariffs.prepaid_tariffs_id
    Where user_tariffs.id=NEw.id;
    
    Update user_tariffs as ut
    Set  ut.rest_messages_counts=messages_counts, ut.rest_calls_time=calls_time, ut.rest_internet=internet
    Where user_tariffs.id=New.id;
    
    
	
End //
DELIMITER ;
