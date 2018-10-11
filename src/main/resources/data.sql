insert into hotel values (1, current_date(), null, 'Test', 'Katowice', 'PL', 'Testowy hotel', '40-123');
insert into room_type values (1, current_date(), null, 3, 'Pokoik taki tam', 70, 0);
insert into room values (1, current_date(), null, 3, '101A', 1, 1);
commit;
