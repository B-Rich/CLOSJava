/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamewho.extTC.TileMap;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

/**
 *
 * @author Threadcount
 */
public class TileMp {

    private double x;
    private double y;
    private int minx;
    private int miny;
    private int maxx;
    private int maxy;
    private double twn;
    private int[][] mp;
    private int tilesz;
    private int rws;
    private int clmns;
    private int wdth;
    private int hght;
    private BufferedImage tilest;
    private Tile[][] tls;
    private int rwoffst;
    private int clmnoffst;
    private int rwsdrw;
    private int clmnsdrw;
    private boolean shake;
    private int intnsfy;
    private int tlsacrss;

    public TileMp(int ts) {
        this.tilesz = ts;
        this.rwsdrw = (240 / ts + 2);
        this.clmnsdrw = (320 / ts + 2);
        this.twn = 0.07000000000000001D;
    }

    public void loadTiles(String t){
        try {
            this.tilest = ImageIO.read(getClass().getResourceAsStream(t));
            //this.tilest = ImageIO.read(new FileInputStream(t));
            this.tlsacrss = (this.tilest.getWidth() / this.tilesz);
            this.tls = new Tile[2][this.tlsacrss];
            for (int c = 0; c < this.tlsacrss; c++) {
                BufferedImage img = this.tilest.getSubimage(
                        c * this.tilesz,
                        0,
                        this.tilesz,
                        this.tilesz);
                this.tls[0][c] = new Tile(img, Tile.Normal);
                
                img = this.tilest.getSubimage(
                        c * this.tilesz,
                        this.tilesz,
                        this.tilesz,
                        this.tilesz);
                this.tls[1][c] = new Tile(img, Tile.Blocked);
            }
        } catch (Exception excptn) {
            excptn.printStackTrace();
        }
    }
    

    public void loadMap(String t) {
        try {
            //InputStream in = new FileInputStream(t);
            InputStream in = getClass().getResourceAsStream(t);
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(in));
            this.clmns = Integer.parseInt(br.readLine());
            this.rws = Integer.parseInt(br.readLine());
            this.mp = new int[this.rws][this.clmns];
            this.wdth = (this.clmns * this.tilesz);
            this.hght = (this.rws * this.tilesz);
            this.minx = (320 - this.wdth);
            this.maxx = 0;
            this.miny = (240 - this.hght);
            this.maxy = 0;
            String dlms = "\\s+";
            for (int r = 0; r < rws; r++) {
                String ln = br.readLine();
                if(ln == null || ln.isEmpty()) {
                System.out.println("Line is empty or null");
            } else {
                System.out.println(ln);
                String[] tk = ln.split(dlms);
                for (int c = 0; c < clmns; c++) {
                    this.mp[r][c] = Integer.parseInt(tk[c]);
                }
            }
        } }
        catch (Exception excptn) {
            excptn.printStackTrace();
        }
    }
    

    public int getTileSize() {
        return this.tilesz;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public int getWidth() {
        return this.wdth;
    }

    public int getHeight() {
        return this.hght;
    }

    public int getNumRows() {
        return this.rws;
    }

    public int getNumCols() {
        return this.clmns;
    }

    public int getType(int r, int c) {
        int rc = this.mp[r][c];
        int x = rc / this.tlsacrss;
        int y = rc % this.tlsacrss;
        return this.tls[x][y].getType();
    }

    public boolean isShaking() {
        return this.shake;
    }

    public void setTwn(double d) {
        this.twn = d;
    }

    public void setShaking(boolean b, int i) {
        this.shake = b;
        this.intnsfy = i;
    }

    public void setBounds(int a, int b, int c, int d) {
        this.minx = (320 - a);
        this.miny = (320 - b);
        this.maxx = c;
        this.maxy = d;
    }

    public void fixBounds() {
        if (this.x < this.minx) {
            this.x = this.minx;
        }
        if (this.y < this.miny) {
            this.y = this.miny;
        }
        if (this.x > this.maxx) {
            this.x = this.maxx;
        }
        if (this.y > this.maxy) {
            this.y = this.maxy;
        }
    }

    public void setPosition(double x, double y) {
        this.x += (x - this.x) * this.twn;
        this.y += (y - this.y) * this.twn;
        fixBounds();
        this.clmnoffst = ((int) -this.x / this.tilesz);
        this.rwoffst = ((int) -this.y / this.tilesz);
    }

    public void update() {
        if (this.shake) {
            this.x += Math.random() * this.intnsfy - this.intnsfy / 2;
            this.y += Math.random() * this.intnsfy - this.intnsfy / 2;
        }
    }

    public void draw(Graphics2D grphcs) {
        for (int r = this.rwoffst; r < this.rwoffst + this.rwsdrw; r++) {
            if (r >= this.rws) {
                break;
            }
            for (int c = this.clmnoffst; c < this.clmnoffst + this.clmnsdrw; c++) {
                if (c >= this.clmns) {
                    break;
                }
                if (this.mp[r][c] != 0) {
                    int rc = this.mp[r][c];
                    int a = rc / this.tlsacrss;
                    int b = rc % this.tlsacrss;
                    grphcs.drawImage(
                            this.tls[a][b].getImage(),
                            (int) this.x + c * this.tilesz,
                            (int) this.y + r * this.tilesz,
                            null);
                }
            }
        }
    }
}
//public class TileMp {
//	
//	// position
//	private double x;
//	private double y;
//	
//	// bounds
//	private int xmin;
//	private int ymin;
//	private int xmax;
//	private int ymax;
//	
//	private double tween;
//	
//	// map
//	private int[][] map;
//	private int tileSize;
//	private int numRows;
//	private int numCols;
//	private int width;
//	private int height;
//	
//	// tileset
//	private BufferedImage tileset;
//	private int numTilesAcross;
//	private Tile[][] tiles;
//	
//	// drawing
//	private int rowOffset;
//	private int colOffset;
//	private int numRowsToDraw;
//	private int numColsToDraw;
//	
//	public TileMp(int tileSize) {
//		this.tileSize = tileSize;
//		numRowsToDraw = 240 / tileSize + 2;
//		numColsToDraw = 320 / tileSize + 2;
//		tween = 0.07;
//	}
//	
//	public void loadTiles(String s) {
//		
//		try {
//
//			tileset = ImageIO.read(
//				getClass().getResourceAsStream(s)
//			);
//			numTilesAcross = tileset.getWidth() / tileSize;
//			tiles = new Tile[2][numTilesAcross];
//			
//			BufferedImage subimage;
//			for(int col = 0; col < numTilesAcross; col++) {
//				subimage = tileset.getSubimage(
//							col * tileSize,
//							0,
//							tileSize,
//							tileSize
//						);
//				tiles[0][col] = new Tile(subimage, Tile.Normal);
//				subimage = tileset.getSubimage(
//							col * tileSize,
//							tileSize,
//							tileSize,
//							tileSize
//						);
//				tiles[1][col] = new Tile(subimage, Tile.Blocked);
//			}
//			
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
//	
//	public void loadMap(String s) {
//		
//		try {
//			
//			InputStream in = getClass().getResourceAsStream(s);
//			BufferedReader br = new BufferedReader(
//						new InputStreamReader(in)
//					);
//			
//			numCols = Integer.parseInt(br.readLine());
//			numRows = Integer.parseInt(br.readLine());
//			map = new int[numRows][numCols];
//			width = numCols * tileSize;
//			height = numRows * tileSize;
//			
//			xmin = 240 - width;
//			xmax = 0;
//			ymin = 320 - height;
//			ymax = 0;
//			
//			String delims = "\\s+";
//			for(int row = 0; row < numRows; row++) {
//				String line = br.readLine();
//				String[] tokens = line.split(delims);
//				for(int col = 0; col < numCols; col++) {
//					map[row][col] = Integer.parseInt(tokens[col]);
//				}
//			}
//			
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
//	
//	public int getTileSize() { return tileSize; }
//	public double getx() { return x; }
//	public double gety() { return y; }
//	public int getWidth() { return width; }
//	public int getHeight() { return height; }
//	
//	public int getType(int row, int col) {
//		int rc = map[row][col];
//		int r = rc / numTilesAcross;
//		int c = rc % numTilesAcross;
//		return tiles[r][c].getType();
//	}
//	
//	public void setTween(double d) { tween = d; }
//	
//	public void setPosition(double x, double y) {
//		
//		this.x += (x - this.x) * tween;
//		this.y += (y - this.y) * tween;
//		
//		fixBounds();
//		
//		colOffset = (int)-this.x / tileSize;
//		rowOffset = (int)-this.y / tileSize;
//		
//	}
//	
//	private void fixBounds() {
//		if(x < xmin) x = xmin;
//		if(y < ymin) y = ymin;
//		if(x > xmax) x = xmax;
//		if(y > ymax) y = ymax;
//	}
//	
//	public void draw(Graphics2D g) {
//		
//		for(
//			int row = rowOffset;
//			row < rowOffset + numRowsToDraw;
//			row++) {
//			
//			if(row >= numRows) break;
//			
//			for(
//				int col = colOffset;
//				col < colOffset + numColsToDraw;
//				col++) {
//				
//				if(col >= numCols) break;
//				
//				if(map[row][col] == 0) continue;
//				
//				int rc = map[row][col];
//				int r = rc / numTilesAcross;
//				int c = rc % numTilesAcross;
//				
//				g.drawImage(
//					tiles[r][c].getImage(),
//					(int)x + col * tileSize,
//					(int)y + row * tileSize,
//					null
//				);
//				
//			}
//			
//		}
//		
//	}
//	
//}