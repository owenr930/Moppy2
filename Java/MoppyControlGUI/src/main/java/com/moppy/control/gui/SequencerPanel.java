package com.moppy.control.gui;

import com.moppy.control.MoppyPreferences;
import com.moppy.core.midi.MoppyMIDISequencer;
import com.moppy.core.status.StatusConsumer;
import com.moppy.core.status.StatusUpdate;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.InvalidMidiDataException;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * 
 */
public class SequencerPanel extends JPanel implements StatusConsumer, ActionListener {

    private static final String TIME_CODE_FORMAT = "%d:%02d";
    private static final String SEQUENCE_PROGRESS = "SeqProgCmd";
    private static final String BTN_PLAY = "⏵";
    private static final String BTN_PAUSE = "⏸";
    
    private final MoppyMIDISequencer midiSequencer;
    private final Timer sequenceProgressUpdateTimer;
    
    /**
     * Creates new form SequencerPanel
     */
    public SequencerPanel(MoppyMIDISequencer midiSequencer) {
        this.midiSequencer = midiSequencer;
        
        sequenceProgressUpdateTimer = new Timer(500, this);
        sequenceProgressUpdateTimer.setActionCommand(SEQUENCE_PROGRESS);
        
        initComponents();
        setControlsEnabled(false); // Leave these disabled until we've loaded a sequence
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sequenceFileChooser = new javax.swing.JFileChooser();
        fileNameLabel = new javax.swing.JLabel();
        loadFileButton = new javax.swing.JButton();
        controlsPane = new javax.swing.JPanel();
        sequenceCurrentTimeLabel = new javax.swing.JLabel();
        sequenceSlider = new javax.swing.JSlider();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        sequenceTotalTimeLabel = new javax.swing.JLabel();
        stopButton = new javax.swing.JButton();
        playButton = new javax.swing.JButton();

        sequenceFileChooser.setCurrentDirectory(new File(MoppyPreferences.getString(MoppyPreferences.LOAD_FILE_DIR, ".")));
        sequenceFileChooser.setDialogTitle("Select MIDI File");
        sequenceFileChooser.setFileFilter(new FileNameExtensionFilter("MIDI Files", "mid"));

        setMinimumSize(new java.awt.Dimension(400, 200));
        setName("sequencerPanel"); // NOI18N

        fileNameLabel.setText("No file loaded...");
        fileNameLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        loadFileButton.setText("Load File");
        loadFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadFileButtonActionPerformed(evt);
            }
        });

        sequenceCurrentTimeLabel.setText("00:00");

        sequenceSlider.setMajorTickSpacing(60);
        sequenceSlider.setMaximum(120);
        sequenceSlider.setMinorTickSpacing(15);
        sequenceSlider.setPaintTicks(true);
        sequenceSlider.setValue(0);
        sequenceSlider.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                sequenceSliderMouseDragged(evt);
            }
        });
        sequenceSlider.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                sequenceSliderMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sequenceSliderMouseReleased(evt);
            }
        });

        sequenceTotalTimeLabel.setText("00:00");

        stopButton.setText("⏹");
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });

        playButton.setFont(playButton.getFont().deriveFont(playButton.getFont().getSize()+6f));
        playButton.setText("⏵");
        playButton.setToolTipText("");
        playButton.setMaximumSize(new java.awt.Dimension(49, 23));
        playButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout controlsPaneLayout = new javax.swing.GroupLayout(controlsPane);
        controlsPane.setLayout(controlsPaneLayout);
        controlsPaneLayout.setHorizontalGroup(
            controlsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlsPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(controlsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(controlsPaneLayout.createSequentialGroup()
                        .addComponent(sequenceCurrentTimeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(175, 175, 175))
                    .addGroup(controlsPaneLayout.createSequentialGroup()
                        .addComponent(stopButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(controlsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(controlsPaneLayout.createSequentialGroup()
                                .addComponent(sequenceSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sequenceTotalTimeLabel))
                            .addGroup(controlsPaneLayout.createSequentialGroup()
                                .addComponent(playButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        controlsPaneLayout.setVerticalGroup(
            controlsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlsPaneLayout.createSequentialGroup()
                .addGroup(controlsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sequenceSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sequenceTotalTimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(controlsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(controlsPaneLayout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(controlsPaneLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(playButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(controlsPaneLayout.createSequentialGroup()
                .addComponent(sequenceCurrentTimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(stopButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fileNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loadFileButton)
                .addContainerGap())
            .addComponent(controlsPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loadFileButton)
                    .addComponent(fileNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(controlsPane, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void loadFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadFileButtonActionPerformed
        if (JFileChooser.APPROVE_OPTION == sequenceFileChooser.showOpenDialog(this)) {
            try {
                File selectedFile = sequenceFileChooser.getSelectedFile();
                midiSequencer.loadSequence(selectedFile);
                fileNameLabel.setText(selectedFile.getName());
                MoppyPreferences.setString(MoppyPreferences.LOAD_FILE_DIR, selectedFile.getParentFile().getAbsolutePath());
            } catch (IOException | InvalidMidiDataException ex) {
                Logger.getLogger(SequencerPanel.class.getName()).log(Level.WARNING, null, ex);
            }
        }
    }//GEN-LAST:event_loadFileButtonActionPerformed

    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed
        midiSequencer.stop();
    }//GEN-LAST:event_stopButtonActionPerformed

    private void playButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playButtonActionPerformed
        if (midiSequencer.isPlaying()) {
            midiSequencer.pause();
        }  else {
            midiSequencer.play();
        }
    }//GEN-LAST:event_playButtonActionPerformed

    private void sequenceSliderMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sequenceSliderMouseDragged
        midiSequencer.setSecondsPosition(sequenceSlider.getValue());
        Duration length = Duration.ofSeconds(midiSequencer.getSecondsPosition());
        sequenceCurrentTimeLabel.setText(String.format(TIME_CODE_FORMAT, length.toMinutes(), length.getSeconds()));
    }//GEN-LAST:event_sequenceSliderMouseDragged

    private void sequenceSliderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sequenceSliderMousePressed
        midiSequencer.pause();
    }//GEN-LAST:event_sequenceSliderMousePressed

    private void sequenceSliderMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sequenceSliderMouseReleased
        sequenceSliderMouseDragged(evt); // Update position
        midiSequencer.play();
    }//GEN-LAST:event_sequenceSliderMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel controlsPane;
    private javax.swing.JLabel fileNameLabel;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton loadFileButton;
    private javax.swing.JButton playButton;
    private javax.swing.JLabel sequenceCurrentTimeLabel;
    private javax.swing.JFileChooser sequenceFileChooser;
    private javax.swing.JSlider sequenceSlider;
    private javax.swing.JLabel sequenceTotalTimeLabel;
    private javax.swing.JButton stopButton;
    // End of variables declaration//GEN-END:variables

    @Override
    public void receiveUpdate(StatusUpdate update) {
        switch (update.getType()) {
            case SEQUENCE_LOAD:
                Duration length = Duration.ofSeconds(midiSequencer.getSecondsLength());
                sequenceSlider.setMaximum((int) length.getSeconds());
                sequenceTotalTimeLabel.setText(String.format(TIME_CODE_FORMAT, length.toMinutes(), length.getSeconds()%60));
                setControlsEnabled(true);
                break;
            case SEQUENCE_START:
                playButton.setText(BTN_PAUSE);
                sequenceProgressUpdateTimer.start();
                break;
            case SEQUENCE_END:
                sequenceSlider.setValue(0);
                sequenceCurrentTimeLabel.setText(String.format(TIME_CODE_FORMAT, 0, 0));
            case SEQUENCE_PAUSE:
                playButton.setText(BTN_PLAY);
                sequenceProgressUpdateTimer.stop();
                break;
        }
    }
    
    private void setControlsEnabled(boolean enabled) {
        Arrays.stream(controlsPane.getComponents()).forEach(c -> c.setEnabled(enabled));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (SEQUENCE_PROGRESS.equals(e.getActionCommand())) {
            Duration length = Duration.ofSeconds(midiSequencer.getSecondsPosition());
            sequenceSlider.setValue((int) length.getSeconds());
            sequenceCurrentTimeLabel.setText(String.format(TIME_CODE_FORMAT, length.toMinutes(), length.getSeconds()%60));
        }
    }
}
