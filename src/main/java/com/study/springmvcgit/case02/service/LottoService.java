package com.study.springmvcgit.case02.service;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;


@Service
public class LottoService {
	// �]�����X���୫�� �ҥHlist�̭��O�]set
	private static List<Set<Integer>> lottos = new ArrayList<Set<Integer>>();
	
	private Map<Integer, Integer> countL = new HashMap<Integer, Integer>();
	
	public List<Set<Integer>> getLottos(){
		return lottos;
	}
	//�[�J���X
	public void addLotto() {
		lottos.add(0, generateLotto());
	}
	//�ק︹�X
	public void updateLotto(int index) {
		lottos.set(index, generateLotto());
		m.clear();
	}
	//�R�����X
	public void deleteLotto(int index) {
		lottos.remove(index);
		m.clear();
		
	}
	//1~39 �H�����X�����ƪ�5�Ӹ��X
	private Set<Integer> generateLotto(){
		Random r = new Random();
		Set<Integer> lotto = new LinkedHashSet<Integer>();
		while(lotto.size() < 5) {
			lotto.add(r.nextInt(39)+1);
		}
		return lotto;
	}
	//�ƧǤ�k
	public static <K,V extends Comparable<? super V>> Map<K,V> sortMapByValue(Map<K, V> m){
		ArrayList<Map.Entry<K, V>> list = new ArrayList<>(m.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {

			@Override
			public int compare(Entry<K, V> o1, Entry<K, V> o2) {
				if(o1.getValue()==null||o2.getValue()==null) {
					return -1;
				}
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		
		Map<K, V> returnMap = new LinkedHashMap<>();
		for(Map.Entry<K, V> entry : list) {
			returnMap.put(entry.getKey(),entry.getValue());
		}
		
		return returnMap;
	}
	
	//���X�ƶq�����X
	private static Map<Integer, Integer> m = new HashMap<>();
	//���o���X�ƶq�����X
	public Map<Integer, Integer> getCount(){
		return m;
	}
	//�p��C�Ӹ��X�ƶq
	public Map countLottos(List<Set<Integer>> l) {
		//�NList<Set<Integer>>�����[��t�@��List�ϸ�ƭ���
		List<Integer> cLotto = new ArrayList();
		l.stream().forEach(c->cLotto.addAll(c));
		
		//�̾ڸ��X�έp�Ӽ�
		Map<Object, List<Integer>> total =cLotto.stream().
				collect(Collectors.groupingBy(Integer::intValue));
		
		//
		for(Object o:total.keySet()) {
			List co = new ArrayList();
			co.add(total.get(o).stream().count());
			int K = Integer.parseInt(o.toString());
			int V = Integer.parseInt(co.get(0).toString());
			m.put(K, V);
		}
		m=sortMapByValue(m);
		return m;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
