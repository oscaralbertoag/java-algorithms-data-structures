package com.oscar.challenge;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class BallotCount {

    public static final String NO_MAJORITY_WINNER = "NONE";

    private static class CandidateCount {
        private String candidate;
        private Long count;

        public CandidateCount(String candidate, Long count) {
            this.candidate = candidate;
            this.count = count;
        }

        public String getCandidate() {
            return candidate;
        }

        public void setCandidate(String candidate) {
            this.candidate = candidate;
        }

        public Long getCount() {
            return count;
        }

        public void setCount(Long count) {
            this.count = count;
        }
    }

    /**
     * Receives ballots in the form ["Candidate 1", "Candidate 2"] - count. Where count is the number of ballots
     * found in the same configuration (in this example, Candidate 1 would get 'count' first votes and Candidate 2 would
     * get 'count' second votes.
     *
     * @param ballots map of ballot lists to counts
     * @return the name of the majority winner if one is found (a majority winner will exist if any candidate gets 50%
     * or more votes; if another candidate gets 50% as well, there will be no majority winner. For no majority winner
     * found, this method will return 'NONE'
     */
    public String calculateMajorityWinner(Map<List<String>, Long> ballots) {
        if (ballots == null || ballots.isEmpty()) return NO_MAJORITY_WINNER;

        long totalCount = 0;

        // Step 1: find the top candidate in every ballot
        Map<String, Long> candidateCounts = new HashMap<>();

        for (Map.Entry<List<String>, Long> entry : ballots.entrySet()) {
            String currentBallotTopCandidate = entry.getKey().get(0);
            long votesEarnedInCurrentBallot = entry.getValue();
            candidateCounts.put(currentBallotTopCandidate,
                    candidateCounts.getOrDefault(currentBallotTopCandidate, 0L) + votesEarnedInCurrentBallot);

            totalCount += votesEarnedInCurrentBallot;
        }

        // Step 2: find the top candidate overall
        PriorityQueue<CandidateCount> maxHeap = new PriorityQueue<>(
                (o1, o2) -> Long.compare(o2.getCount(), o1.getCount()));

        for (Map.Entry<String, Long> candidateCount : candidateCounts.entrySet()) {
            maxHeap.add(new CandidateCount(candidateCount.getKey(), candidateCount.getValue()));
        }

        CandidateCount topCandidateCount = maxHeap.remove();

        if (!maxHeap.isEmpty()) {
            CandidateCount secondCandidateCount = maxHeap.remove();
            if (secondCandidateCount.getCount().equals(topCandidateCount.getCount())) {
                return NO_MAJORITY_WINNER;
            }
        }

        long halfVotes = Double.valueOf(Math.ceil((double) totalCount / 2)).longValue();
        return topCandidateCount.getCount() >= halfVotes ? topCandidateCount.getCandidate() : NO_MAJORITY_WINNER;
    }


}
