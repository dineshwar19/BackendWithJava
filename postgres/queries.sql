-- update demo set newcol = '{{11,11} ,{12,12}}' where id = 1;
-- DELETE FROM demo WHERE jsondata IS NULL AND arraydata IS NULL;
-- select row_to_json(demo) from demo;
select * from demo;
-- SELECT jsonb_build_object('id', id, 'jsondata', jsondata, 'arraydata', arraydata, 'newcol', newcol) AS json_object
-- FROM demo;
-- insert into demo(id,jsondata,arraydata,newcol,buildobj) values(2,'{"age":22,"name":"demo2"}','[1,2,3,4]','{1,2,3,4,5,6}','[12,23,34,45]');
-- select jsonb_populate_record(demo ,jsondata) from demo where id=2;
select jsonb_array_elements(arraydata) from demo where id=1;
select * from demo where (jsondata ->> 'name')::varchar = 'demo2';
