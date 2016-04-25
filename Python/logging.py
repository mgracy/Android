print('this is the first line')
import logging 
import pdb
a = "aaa"
print('after import pdb')
print('a:',a)
pdb.set_trace() 
 b = "bbb"
 c = "ccc"
 final = a + b + c 
 print final
 # LOG1=logging.getLogger('b.c') 
 # LOG2=logging.getLogger('d.e') 
 # filehandler = logging.FileHandler('test.log','a') 
 # formatter = logging.Formatter('%(name)s %(asctime)s %(levelname)s %(message)s') 
 # filehandler.setFormatter(formatter) 
 # filter=logging.Filter('b') 
 # filehandler.addFilter(filter) 
 # LOG1.addHandler(filehandler) 
 # LOG2.addHandler(filehandler) 
 # LOG1.setLevel(logging.INFO) 
 # LOG2.setLevel(logging.DEBUG) 
 # LOG1.debug('it is a debug info for log1') 
 # LOG1.info('normal infor for log1') 
 # LOG1.warning('warning info for log1:b.c') 
 # LOG1.error('error info for log1:abcd') 
 # LOG1.critical('critical info for log1:not worked') 
 # LOG2.debug('debug info for log2') 
 # LOG2.info('normal info for log2') 
 # LOG2.warning('warning info for log2') 
 # LOG2.error('error:b.c') 
 # LOG2.critical('critical')